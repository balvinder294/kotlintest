package com.tekraze.kotlinTest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.tekraze.kotlinTest.core.data.job.JobDto
import kotlinx.android.synthetic.main.activity_job_detail.*
import java.util.*
import com.tekraze.kotlinTest.core.data.enum.*
import com.tekraze.kotlinTest.core.l18n.EnumLocalization
import com.tekraze.kotlinTest.viewadapters.ObjectConverterStringAdapter

class JobDetailActivity : BaseActivity() {

    private var isSaving: Boolean = false
    private lateinit var item: JobDto
    private var isNew: Boolean = false

    private val pendingDependencies = HashSet<String>()
    
    companion object {
        private const val ITEM_EXTRA = "ItemExtra"

        fun newIntent(from: Context): Intent {
            return Intent(from,JobDetailActivity::class.java)
        }
        fun editIntent(from: Context, item: JobDto): Intent {
            val intent = Intent(from,JobDetailActivity::class.java)
            intent.putExtra(ITEM_EXTRA,item)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        if (intent.hasExtra(ITEM_EXTRA)){
            item = intent.extras.getSerializable(ITEM_EXTRA) as JobDto
            isNew = false
            populate(item)
        }
        else{
            isNew = true
            item = JobDto()
        }

        save_button.setOnClickListener { attempSave() }
        
        spinner_jobTags.adapter = ObjectConverterStringAdapter<Set&lt;String&gt;>(this,Set&lt;String&gt;.values().toMutableList(),{ item -> EnumLocalization.localizeSet&lt;String&gt;(item,this)})
        
        spinner_createdAt.adapter = ObjectConverterStringAdapter<LocalDate>(this,LocalDate.values().toMutableList(),{ item -> EnumLocalization.localizeLocalDate(item,this)})
        
        spinner_modifiedAt.adapter = ObjectConverterStringAdapter<LocalDate>(this,LocalDate.values().toMutableList(),{ item -> EnumLocalization.localizeLocalDate(item,this)})
        

        
    }
  

    private fun populate(item: JobDto) {

    
    
      input_jobName.setText(item.jobName?:"")
      
    
    
      input_companyName.setText(item.companyName?:"")
      
    
    
      input_location.setText(item.location?:"")
      
    
    
      input_positionType.setText(item.positionType?:"")
      
    
    
      spinner_jobTags.setSelection( item.jobTags?.ordinal?:0 )
      
    
    
      input_jobDescription.setText(item.jobDescription?:"")
      
    
    
      input_applyUrl.setText(item.applyUrl?:"")
      
    
    
      input_createdBy.setText(item.createdBy?:"")
      
    
    
      spinner_createdAt.setSelection( item.createdAt?.ordinal?:0 )
      
    
    
      input_modifiedBy.setText(item.modifiedBy?:"")
      
    
    
      spinner_modifiedAt.setSelection( item.modifiedAt?.ordinal?:0 )
      
    

    }

    private fun parseDate(dateTime: String): Date? {
      val date = dateTime.split(' ').firstOrNull()
      val time = dateTime.split(' ').lastOrNull()

      val calendar = Calendar.getInstance()
      calendar.time = DateFormat.getDateFormat(this).parse(date)

      val timeCalendar = Calendar.getInstance()
      timeCalendar.time = DateFormat.getTimeFormat(this).parse(time)

      calendar.set(Calendar.HOUR_OF_DAY,timeCalendar.get(Calendar.HOUR_OF_DAY))
      calendar.set(Calendar.MINUTE,timeCalendar.get(Calendar.MINUTE))
      calendar.set(Calendar.SECOND,timeCalendar.get(Calendar.SECOND))

      return calendar.time
    }

    private fun attempSave() {
        if (isSaving) {
            return
        }

        
        input_jobName.error = null
        
        item.jobName = input_jobName.text.toString()
          
        
        input_companyName.error = null
        
        item.companyName = input_companyName.text.toString()
          
        
        input_location.error = null
        
        item.location = input_location.text.toString()
          
        
        input_positionType.error = null
        
        item.positionType = input_positionType.text.toString()
          
        
        input_jobTags.error = null
        
          item.jobTags = Set&lt;String&gt;.values()[spinner_jobTags.selectedItemPosition ]
          
        
        input_jobDescription.error = null
        
        item.jobDescription = input_jobDescription.text.toString()
          
        
        input_applyUrl.error = null
        
        item.applyUrl = input_applyUrl.text.toString()
          
        
        input_createdBy.error = null
        
        item.createdBy = input_createdBy.text.toString()
          
        
        input_createdAt.error = null
        
          item.createdAt = LocalDate.values()[spinner_createdAt.selectedItemPosition ]
          
        
        input_modifiedBy.error = null
        
        item.modifiedBy = input_modifiedBy.text.toString()
          
        
        input_modifiedAt.error = null
        
          item.modifiedAt = LocalDate.values()[spinner_modifiedAt.selectedItemPosition ]
          
        


        var cancel = false
        var focusView: View? = null

        
        if (!isJobNameValid(item.jobName)) {
          input_jobName.error = getString(R.string.error_job_invalid_jobName)
          focusView = input_jobName
          cancel = true
        }
        
        if (!isCompanyNameValid(item.companyName)) {
          input_companyName.error = getString(R.string.error_job_invalid_companyName)
          focusView = input_companyName
          cancel = true
        }
        
        if (!isLocationValid(item.location)) {
          input_location.error = getString(R.string.error_job_invalid_location)
          focusView = input_location
          cancel = true
        }
        
        if (!isPositionTypeValid(item.positionType)) {
          input_positionType.error = getString(R.string.error_job_invalid_positionType)
          focusView = input_positionType
          cancel = true
        }
        
        if (!isJobTagsValid(item.jobTags)) {
          input_jobTags.error = getString(R.string.error_job_invalid_jobTags)
          focusView = input_jobTags
          cancel = true
        }
        
        if (!isJobDescriptionValid(item.jobDescription)) {
          input_jobDescription.error = getString(R.string.error_job_invalid_jobDescription)
          focusView = input_jobDescription
          cancel = true
        }
        
        if (!isApplyUrlValid(item.applyUrl)) {
          input_applyUrl.error = getString(R.string.error_job_invalid_applyUrl)
          focusView = input_applyUrl
          cancel = true
        }
        
        if (!isCreatedByValid(item.createdBy)) {
          input_createdBy.error = getString(R.string.error_job_invalid_createdBy)
          focusView = input_createdBy
          cancel = true
        }
        
        if (!isCreatedAtValid(item.createdAt)) {
          input_createdAt.error = getString(R.string.error_job_invalid_createdAt)
          focusView = input_createdAt
          cancel = true
        }
        
        if (!isModifiedByValid(item.modifiedBy)) {
          input_modifiedBy.error = getString(R.string.error_job_invalid_modifiedBy)
          focusView = input_modifiedBy
          cancel = true
        }
        
        if (!isModifiedAtValid(item.modifiedAt)) {
          input_modifiedAt.error = getString(R.string.error_job_invalid_modifiedAt)
          focusView = input_modifiedAt
          cancel = true
        }
        

        if (cancel) {
            focusView?.requestFocus()
        } else {
            showProgress(true)
            isSaving=true

            val service = getCore().jobService()

            if(isNew) {
                service.create(item,{ saved -> onSaveSuccess(saved) }, {statusCode, response -> onSaveError(response) })
            }
            else{
                service.update(item,{ saved -> onSaveSuccess(saved) }, {statusCode, response -> onSaveError(response) })
            }
        }
    }

    private fun onSaveError(response: String) {
        showProgress(false)
        isSaving=false
        Toast.makeText(this,R.string.save_error,Toast.LENGTH_SHORT).show()
    }

    private fun onSaveSuccess(JobDto: JobDto) {
        isSaving=false
        finish()
    }
    

    private fun isJobNameValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isCompanyNameValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isLocationValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isPositionTypeValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isJobTagsValid(field: Set&lt;String&gt;?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isJobDescriptionValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isApplyUrlValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isCreatedByValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isCreatedAtValid(field: LocalDate?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isModifiedByValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isModifiedAtValid(field: LocalDate?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        entity_form.visibility = if (show) View.GONE else View.VISIBLE
        entity_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        entity_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        save_progress.visibility = if (show) View.VISIBLE else View.GONE
        save_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        save_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
    }

}
