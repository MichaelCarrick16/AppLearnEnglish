package com.example.applearnenglish

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LearnEnglish")
class VocaModel(@ColumnInfo(name = "voca_col") var voca : String = "") {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_col")
    var id : Int = 0


}