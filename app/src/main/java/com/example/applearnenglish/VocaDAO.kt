package com.example.applearnenglish

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VocaDAO {

    @Query("Select *From LearnEnglish")
    fun getAll() : MutableList<VocaModel>

    @Insert
    fun insertVoca(voCaModel : VocaModel)
}