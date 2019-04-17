package com.tekraze.kotlinTest.core.l18n

import android.content.Context
import com.tekraze.kotlinTest.R
import com.tekraze.kotlinTest.core.data.enum.LocalDate
import com.tekraze.kotlinTest.core.data.enum.LocalDate
import com.tekraze.kotlinTest.core.data.enum.LocalDate
import com.tekraze.kotlinTest.core.data.enum.Set<String>
import com.tekraze.kotlinTest.core.data.enum.LocalDate
import com.tekraze.kotlinTest.core.data.enum.LocalDate
//imports-needle

class EnumLocalization{
    companion object {
                 fun localizeLocalDate(item: LocalDate?, with: Context) : String {
            return when(item){
LocalDate.dateOfBirth -> with.getString(R.string.local_date_dateofbirth)
                else -> {
                    ""
                }
            }
        }
                 fun localizeLocalDate(item: LocalDate?, with: Context) : String {
            return when(item){
LocalDate.contractClosingDate -> with.getString(R.string.local_date_contractclosingdate)
                else -> {
                    ""
                }
            }
        }
                 fun localizeLocalDate(item: LocalDate?, with: Context) : String {
            return when(item){
LocalDate.resumeFileUploadedOn -> with.getString(R.string.local_date_resumefileuploadedon)
                else -> {
                    ""
                }
            }
        }
                 fun localizeSet<String>(item: Set<String>?, with: Context) : String {
            return when(item){
Set<String>.jobTags -> with.getString(R.string.set<_string>_jobtags)
                else -> {
                    ""
                }
            }
        }
                 fun localizeLocalDate(item: LocalDate?, with: Context) : String {
            return when(item){
LocalDate.createdAt -> with.getString(R.string.local_date_createdat)
                else -> {
                    ""
                }
            }
        }
                 fun localizeLocalDate(item: LocalDate?, with: Context) : String {
            return when(item){
LocalDate.modifiedAt -> with.getString(R.string.local_date_modifiedat)
                else -> {
                    ""
                }
            }
        }
        //functions-needle






    }
}
