package com.itachi.covidapp.custom

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Bundle
import android.util.Log
import java.util.*


class InMemoryCursor
/**
 * Creates a new cursor setting the columns names that will be used by this cursor. The
 * length of this array determines the number of columns that the cursor can handle.
 * Filling that below this number will set the rest of the values to null, and all
 * items above it will be ignored.
 * @param columnNames the columns names of this cursor.
 */
    (private val mColumnNames: Array<String>) : Cursor {
    private val mRecords = Collections.synchronizedList(ArrayList<Record>())
    private var mCurrentPosition: Int = 0

    private val mObservers = ArrayList<DataSetObserver>()
    private var mNotificationUri: Uri? = null
    private val mExtras = Bundle()

    private class Record {
        var mFields: Array<Any>? = null
    }

    init {
        mCurrentPosition = -1
    }

    /**
     * Adds all the [List] as new rows of the cursor.
     */
    fun addAll(data: List<Array<Any>>) {
        for (fields in data) {
            internalAdd(fields)
        }
        notifyObservers()
    }

    /**
     * Adds a new row to the cursor.
     */
    fun add(data: Array<Any>) {
        internalAdd(data)
        notifyObservers()
    }

    private fun internalAdd(data: Array<Any>) {
        val record = Record()
        record.mFields = Array(mColumnNames.size){Any()}
        val count = Math.min(record.mFields!!.size, data.size)
        System.arraycopy(data, 0, record.mFields, 0, count)
        val updatePosition = mRecords.size == 0
        mRecords.add(record)
        if (updatePosition) {
            mCurrentPosition = 0
        }
    }

    /**
     * Update the cursor with the given data at the position passed as argument.
     * @return if the row was found and updated.
     */
    fun update(position: Int, data: Array<Any>): Boolean {
        if (position >= 0 && position < mRecords.size) {
            val record = mRecords[position]
            val count = Math.min(record.mFields!!.size, data.size)
            System.arraycopy(data, 0, record.mFields, 0, count)
            notifyObservers()
            return true
        }
        return false
    }

    /**
     * Remove the row at the passed position from the cursor if exists.
     * @return if the row was found and deleted.
     */
    fun remove(position: Int): Boolean {
        if (position >= 0 && position < mRecords.size) {
            mRecords.removeAt(position)
            if (mRecords.size == 0) {
                mCurrentPosition = -1
            } else if (mCurrentPosition >= mRecords.size) {
                mCurrentPosition--
            }
            notifyObservers()
            return true
        }
        return false
    }

    /**
     * Clear the internal cursor data.
     */
    fun removeAll() {
        if (mRecords.size >= 0) {
            mRecords.clear()
            mCurrentPosition = -1
            notifyObservers()
        }
    }

    /** {@inheritDoc}  */
    override fun getCount(): Int {
        return mRecords.size
    }

    /** {@inheritDoc}  */
    override fun getPosition(): Int {
        return mCurrentPosition
    }

    /** {@inheritDoc}  */
    override fun move(offset: Int): Boolean {
        Log.d("test---","offset $offset")
        val next = mCurrentPosition + offset
        if (next < 0 || next >= mRecords.size) {
            return false
        }
        mCurrentPosition = next
        return true
    }

    /** {@inheritDoc}  */
    override fun moveToPosition(position: Int): Boolean {
        Log.d("test---","position $position")
        if (position < 0 || position >= mRecords.size) {
            return false
        }
        mCurrentPosition = position
        return true
    }

    /** {@inheritDoc}  */
    override fun moveToFirst(): Boolean {
        if (mRecords.size == 0) {
            return false
        }
        mCurrentPosition = 0
        return true
    }

    /** {@inheritDoc}  */
    override fun moveToLast(): Boolean {
        if (mRecords.size == 0) {
            return false
        }
        mCurrentPosition = mRecords.size - 1
        return true
    }

    /** {@inheritDoc}  */
    override fun moveToNext(): Boolean {
        return move(1)
    }

    /** {@inheritDoc}  */
    override fun moveToPrevious(): Boolean {
        return move(-1)
    }

    /** {@inheritDoc}  */
    override fun isFirst(): Boolean {
        return mCurrentPosition == 0
    }

    /** {@inheritDoc}  */
    override fun isLast(): Boolean {
        return mCurrentPosition == mRecords.size - 1
    }

    /** {@inheritDoc}  */
    override fun isBeforeFirst(): Boolean {
        return mCurrentPosition < 0
    }

    /** {@inheritDoc}  */
    override fun isAfterLast(): Boolean {
        return mCurrentPosition >= mRecords.size
    }

    /** {@inheritDoc}  */
    override fun getColumnIndex(columnName: String): Int {
        var i = 0
        for (name in mColumnNames) {
            if (name.equals(columnName, ignoreCase = true)) {
                return i
            }
            i++
        }
        return -1
    }

    /** {@inheritDoc}  */
    @Throws(IllegalArgumentException::class)
    override fun getColumnIndexOrThrow(columnName: String): Int {
        val columnIndex = getColumnIndex(columnName)
        require(columnIndex != -1)
        return columnIndex
    }

    /** {@inheritDoc}  */
    override fun getColumnName(columnIndex: Int): String {
        return mColumnNames[columnIndex]
    }

    override fun getColumnNames(): Array<String> {
        return mColumnNames
    }

    /** {@inheritDoc}  */
    override fun getColumnCount(): Int {
        return mColumnNames.size
    }

    /** {@inheritDoc}  */
    override fun getBlob(columnIndex: Int): ByteArray? {
        return if (isNull(columnIndex)) {
            null
        } else mRecords[mCurrentPosition].mFields!![columnIndex] as ByteArray
    }

    /** {@inheritDoc}  */
    override fun getString(columnIndex: Int): String? {
        if (isNull(columnIndex)) {
            return null
        }
        val o = mRecords[mCurrentPosition].mFields!![columnIndex]
        return o.toString()
    }

    /** {@inheritDoc}  */
    override fun copyStringToBuffer(columnIndex: Int, buffer: CharArrayBuffer) {
        val o = mRecords[mCurrentPosition].mFields!![columnIndex]
        if (o == null) {
            buffer.sizeCopied = 0
            return
        }
        val data: CharArray
        if (o is ByteArray) {
            data = String(o).toCharArray()
        } else {
            data = o.toString().toCharArray()
        }
        if (buffer.data.size >= data.size) {
            System.arraycopy(buffer.data, 0, data, 0, data.size)
            buffer.sizeCopied = data.size
        } else {
            buffer.data = data
            buffer.sizeCopied = data.size
        }
    }

    /** {@inheritDoc}  */
    override fun getShort(columnIndex: Int): Short {
        return getDouble(columnIndex).toShort()
    }

    /** {@inheritDoc}  */
    override fun getInt(columnIndex: Int): Int {
        return getDouble(columnIndex).toInt()
    }

    /** {@inheritDoc}  */
    override fun getLong(columnIndex: Int): Long {
        return getDouble(columnIndex).toLong()
    }

    /** {@inheritDoc}  */
    override fun getFloat(columnIndex: Int): Float {
        return getDouble(columnIndex).toFloat()
    }

    /** {@inheritDoc}  */
    override fun getDouble(columnIndex: Int): Double {
        val o = mRecords[mCurrentPosition].mFields!![columnIndex]
        return java.lang.Double.parseDouble(o.toString())
    }

    /** {@inheritDoc}  */
    override fun getType(columnIndex: Int): Int {
        val o = mRecords[mCurrentPosition].mFields!![columnIndex] ?: return Cursor.FIELD_TYPE_NULL
        if (o is Byte || o is Short || o is Int || o is Long) {
            return Cursor.FIELD_TYPE_INTEGER
        }
        if (o is Float || o is Double) {
            return Cursor.FIELD_TYPE_FLOAT
        }
        return if (o is ByteArray) {
            Cursor.FIELD_TYPE_BLOB
        } else Cursor.FIELD_TYPE_STRING
    }

    /** {@inheritDoc}  */
    override fun isNull(columnIndex: Int): Boolean {
        return mRecords[mCurrentPosition].mFields!![columnIndex] == null
    }

    /** {@inheritDoc}  */
    override fun deactivate() {}

    /** {@inheritDoc}  */
    override fun requery(): Boolean {
        return true
    }

    /** {@inheritDoc}  */
    override fun close() {
        mRecords.clear()
        mCurrentPosition = -1
    }

    /** {@inheritDoc}  */
    override fun isClosed(): Boolean {
        return mCurrentPosition == -1
    }

    /** {@inheritDoc}  */
    override fun registerContentObserver(observer: ContentObserver) {
        // Ignore
    }

    /** {@inheritDoc}  */
    override fun unregisterContentObserver(observer: ContentObserver) {
        // Ignore
    }

    /** {@inheritDoc}  */
    override fun registerDataSetObserver(observer: DataSetObserver) {
        mObservers.add(observer)
    }

    /** {@inheritDoc}  */
    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        mObservers.remove(observer)
    }

    /** {@inheritDoc}  */
    override fun setNotificationUri(cr: ContentResolver, uri: Uri) {
        mNotificationUri = uri
    }

    /** {@inheritDoc}  */
    override fun getNotificationUri(): Uri? {
        return mNotificationUri
    }

    /** {@inheritDoc}  */
    override fun getWantsAllOnMoveCalls(): Boolean {
        return false
    }

    /** {@inheritDoc}  */
    override fun setExtras(extras: Bundle) {
        mExtras.putAll(extras)
    }

    /** {@inheritDoc}  */
    override fun getExtras(): Bundle {
        return mExtras
    }

    /** {@inheritDoc}  */
    override fun respond(extras: Bundle): Bundle {
        return extras
    }

    private fun notifyObservers() {
        for (observer in mObservers) {
            observer.onChanged()
        }
    }
}