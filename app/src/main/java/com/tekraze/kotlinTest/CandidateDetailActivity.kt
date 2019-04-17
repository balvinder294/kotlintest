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
import com.tekraze.kotlinTest.core.data.candidate.CandidateDto
import kotlinx.android.synthetic.main.activity_candidate_detail.*
import java.util.*
import com.tekraze.kotlinTest.core.data.enum.*
import com.tekraze.kotlinTest.core.l18n.EnumLocalization
import com.tekraze.kotlinTest.viewadapters.ObjectConverterStringAdapter

class CandidateDetailActivity : BaseActivity() {

    private var isSaving: Boolean = false
    private lateinit var item: CandidateDto
    private var isNew: Boolean = false

    private val pendingDependencies = HashSet<String>()
    
    companion object {
        private const val ITEM_EXTRA = "ItemExtra"

        fun newIntent(from: Context): Intent {
            return Intent(from,CandidateDetailActivity::class.java)
        }
        fun editIntent(from: Context, item: CandidateDto): Intent {
            val intent = Intent(from,CandidateDetailActivity::class.java)
            intent.putExtra(ITEM_EXTRA,item)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_detail)

        if (intent.hasExtra(ITEM_EXTRA)){
            item = intent.extras.getSerializable(ITEM_EXTRA) as CandidateDto
            isNew = false
            populate(item)
        }
        else{
            isNew = true
            item = CandidateDto()
        }

        save_button.setOnClickListener { attempSave() }
        
        spinner_dateOfBirth.adapter = ObjectConverterStringAdapter<LocalDate>(this,LocalDate.values().toMutableList(),{ item -> EnumLocalization.localizeLocalDate(item,this)})
        
        spinner_contractClosingDate.adapter = ObjectConverterStringAdapter<LocalDate>(this,LocalDate.values().toMutableList(),{ item -> EnumLocalization.localizeLocalDate(item,this)})
        
        spinner_resumeFileUploadedOn.adapter = ObjectConverterStringAdapter<LocalDate>(this,LocalDate.values().toMutableList(),{ item -> EnumLocalization.localizeLocalDate(item,this)})
        

        
    }
  

    private fun populate(item: CandidateDto) {

    
    
      input_firstName.setText(item.firstName?:"")
      
    
    
      input_lastName.setText(item.lastName?:"")
      
    
    
      input_phoneNumber.setText(item.phoneNumber?:"")
      
    
    
      input_email.setText(item.email?:"")
      
    
    
      spinner_dateOfBirth.setSelection( item.dateOfBirth?.ordinal?:0 )
      
    
    
      input_gender.setText(item.gender?:"")
      
    
    
      input_passportNumber.setText(item.passportNumber?:"")
      
    
    
      input_workAuthorisationCategory.setText(item.workAuthorisationCategory?:"")
      
    
    
      input_nationality.setText(item.nationality?:"")
      
    
    
      input_workAuthorisation.setText(item.workAuthorisation?:"")
      
    
    
      input_validity.setText(item.validity?:"")
      
    
    
      input_experienceLevel.setText(item.experienceLevel?:"")
      
    
    
      input_totalExperienceInYears.setText( item.totalExperienceInYears?.toString()?:"" )
      
    
    
      input_totalExperienceInMonths.setText( item.totalExperienceInMonths?.toString()?:"" )
      
    
    
      input_currentSalaryCurrency.setText(item.currentSalaryCurrency?:"")
      
    
    
      input_currentSalary.setText(item.currentSalary?:"")
      
    
    
      input_currentSalaryTimeUnit.setText(item.currentSalaryTimeUnit?:"")
      
    
    
      input_skillTags.setText(item.skillTags?:"")
      
    
    
      input_resumeTitle.setText(item.resumeTitle?:"")
      
    
    
      input_profileSummary.setText(item.profileSummary?:"")
      
    
    
      input_openForPhoneCall.setText( item.openForPhoneCall?.toString()?:"" )
      
    
    
      input_openForEmailCommunication.setText( item.openForEmailCommunication?.toString()?:"" )
      
    
    
      input_isInContract.setText( item.isInContract?.toString()?:"" )
      
    
    
      spinner_contractClosingDate.setSelection( item.contractClosingDate?.ordinal?:0 )
      
    
    
      input_resumeFileName.setText(item.resumeFileName?:"")
      
    
    
      input_resumeFileLocation.setText(item.resumeFileLocation?:"")
      
    
    
      spinner_resumeFileUploadedOn.setSelection( item.resumeFileUploadedOn?.ordinal?:0 )
      
    

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

        
        input_firstName.error = null
        
        item.firstName = input_firstName.text.toString()
          
        
        input_lastName.error = null
        
        item.lastName = input_lastName.text.toString()
          
        
        input_phoneNumber.error = null
        
        item.phoneNumber = input_phoneNumber.text.toString()
          
        
        input_email.error = null
        
        item.email = input_email.text.toString()
          
        
        input_dateOfBirth.error = null
        
          item.dateOfBirth = LocalDate.values()[spinner_dateOfBirth.selectedItemPosition ]
          
        
        input_gender.error = null
        
        item.gender = input_gender.text.toString()
          
        
        input_passportNumber.error = null
        
        item.passportNumber = input_passportNumber.text.toString()
          
        
        input_workAuthorisationCategory.error = null
        
        item.workAuthorisationCategory = input_workAuthorisationCategory.text.toString()
          
        
        input_nationality.error = null
        
        item.nationality = input_nationality.text.toString()
          
        
        input_workAuthorisation.error = null
        
        item.workAuthorisation = input_workAuthorisation.text.toString()
          
        
        input_validity.error = null
        
        item.validity = input_validity.text.toString()
          
        
        input_experienceLevel.error = null
        
        item.experienceLevel = input_experienceLevel.text.toString()
          
        
        input_totalExperienceInYears.error = null
        
          item.totalExperienceInYears = input_totalExperienceInYears.text.toString().toIntOrNull()
          
        
        input_totalExperienceInMonths.error = null
        
          item.totalExperienceInMonths = input_totalExperienceInMonths.text.toString().toIntOrNull()
          
        
        input_currentSalaryCurrency.error = null
        
        item.currentSalaryCurrency = input_currentSalaryCurrency.text.toString()
          
        
        input_currentSalary.error = null
        
        item.currentSalary = input_currentSalary.text.toString()
          
        
        input_currentSalaryTimeUnit.error = null
        
        item.currentSalaryTimeUnit = input_currentSalaryTimeUnit.text.toString()
          
        
        input_skillTags.error = null
        
        item.skillTags = input_skillTags.text.toString()
          
        
        input_resumeTitle.error = null
        
        item.resumeTitle = input_resumeTitle.text.toString()
          
        
        input_profileSummary.error = null
        
        item.profileSummary = input_profileSummary.text.toString()
          
        
        input_openForPhoneCall.error = null
        
          item.openForPhoneCall = input_openForPhoneCall.text.toString()
          
        
        input_openForEmailCommunication.error = null
        
          item.openForEmailCommunication = input_openForEmailCommunication.text.toString()
          
        
        input_isInContract.error = null
        
          item.isInContract = input_isInContract.text.toString()
          
        
        input_contractClosingDate.error = null
        
          item.contractClosingDate = LocalDate.values()[spinner_contractClosingDate.selectedItemPosition ]
          
        
        input_resumeFileName.error = null
        
        item.resumeFileName = input_resumeFileName.text.toString()
          
        
        input_resumeFileLocation.error = null
        
        item.resumeFileLocation = input_resumeFileLocation.text.toString()
          
        
        input_resumeFileUploadedOn.error = null
        
          item.resumeFileUploadedOn = LocalDate.values()[spinner_resumeFileUploadedOn.selectedItemPosition ]
          
        


        var cancel = false
        var focusView: View? = null

        
        if (!isFirstNameValid(item.firstName)) {
          input_firstName.error = getString(R.string.error_candidate_invalid_firstName)
          focusView = input_firstName
          cancel = true
        }
        
        if (!isLastNameValid(item.lastName)) {
          input_lastName.error = getString(R.string.error_candidate_invalid_lastName)
          focusView = input_lastName
          cancel = true
        }
        
        if (!isPhoneNumberValid(item.phoneNumber)) {
          input_phoneNumber.error = getString(R.string.error_candidate_invalid_phoneNumber)
          focusView = input_phoneNumber
          cancel = true
        }
        
        if (!isEmailValid(item.email)) {
          input_email.error = getString(R.string.error_candidate_invalid_email)
          focusView = input_email
          cancel = true
        }
        
        if (!isDateOfBirthValid(item.dateOfBirth)) {
          input_dateOfBirth.error = getString(R.string.error_candidate_invalid_dateOfBirth)
          focusView = input_dateOfBirth
          cancel = true
        }
        
        if (!isGenderValid(item.gender)) {
          input_gender.error = getString(R.string.error_candidate_invalid_gender)
          focusView = input_gender
          cancel = true
        }
        
        if (!isPassportNumberValid(item.passportNumber)) {
          input_passportNumber.error = getString(R.string.error_candidate_invalid_passportNumber)
          focusView = input_passportNumber
          cancel = true
        }
        
        if (!isWorkAuthorisationCategoryValid(item.workAuthorisationCategory)) {
          input_workAuthorisationCategory.error = getString(R.string.error_candidate_invalid_workAuthorisationCategory)
          focusView = input_workAuthorisationCategory
          cancel = true
        }
        
        if (!isNationalityValid(item.nationality)) {
          input_nationality.error = getString(R.string.error_candidate_invalid_nationality)
          focusView = input_nationality
          cancel = true
        }
        
        if (!isWorkAuthorisationValid(item.workAuthorisation)) {
          input_workAuthorisation.error = getString(R.string.error_candidate_invalid_workAuthorisation)
          focusView = input_workAuthorisation
          cancel = true
        }
        
        if (!isValidityValid(item.validity)) {
          input_validity.error = getString(R.string.error_candidate_invalid_validity)
          focusView = input_validity
          cancel = true
        }
        
        if (!isExperienceLevelValid(item.experienceLevel)) {
          input_experienceLevel.error = getString(R.string.error_candidate_invalid_experienceLevel)
          focusView = input_experienceLevel
          cancel = true
        }
        
        if (!isTotalExperienceInYearsValid(item.totalExperienceInYears)) {
          input_totalExperienceInYears.error = getString(R.string.error_candidate_invalid_totalExperienceInYears)
          focusView = input_totalExperienceInYears
          cancel = true
        }
        
        if (!isTotalExperienceInMonthsValid(item.totalExperienceInMonths)) {
          input_totalExperienceInMonths.error = getString(R.string.error_candidate_invalid_totalExperienceInMonths)
          focusView = input_totalExperienceInMonths
          cancel = true
        }
        
        if (!isCurrentSalaryCurrencyValid(item.currentSalaryCurrency)) {
          input_currentSalaryCurrency.error = getString(R.string.error_candidate_invalid_currentSalaryCurrency)
          focusView = input_currentSalaryCurrency
          cancel = true
        }
        
        if (!isCurrentSalaryValid(item.currentSalary)) {
          input_currentSalary.error = getString(R.string.error_candidate_invalid_currentSalary)
          focusView = input_currentSalary
          cancel = true
        }
        
        if (!isCurrentSalaryTimeUnitValid(item.currentSalaryTimeUnit)) {
          input_currentSalaryTimeUnit.error = getString(R.string.error_candidate_invalid_currentSalaryTimeUnit)
          focusView = input_currentSalaryTimeUnit
          cancel = true
        }
        
        if (!isSkillTagsValid(item.skillTags)) {
          input_skillTags.error = getString(R.string.error_candidate_invalid_skillTags)
          focusView = input_skillTags
          cancel = true
        }
        
        if (!isResumeTitleValid(item.resumeTitle)) {
          input_resumeTitle.error = getString(R.string.error_candidate_invalid_resumeTitle)
          focusView = input_resumeTitle
          cancel = true
        }
        
        if (!isProfileSummaryValid(item.profileSummary)) {
          input_profileSummary.error = getString(R.string.error_candidate_invalid_profileSummary)
          focusView = input_profileSummary
          cancel = true
        }
        
        if (!isOpenForPhoneCallValid(item.openForPhoneCall)) {
          input_openForPhoneCall.error = getString(R.string.error_candidate_invalid_openForPhoneCall)
          focusView = input_openForPhoneCall
          cancel = true
        }
        
        if (!isOpenForEmailCommunicationValid(item.openForEmailCommunication)) {
          input_openForEmailCommunication.error = getString(R.string.error_candidate_invalid_openForEmailCommunication)
          focusView = input_openForEmailCommunication
          cancel = true
        }
        
        if (!isIsInContractValid(item.isInContract)) {
          input_isInContract.error = getString(R.string.error_candidate_invalid_isInContract)
          focusView = input_isInContract
          cancel = true
        }
        
        if (!isContractClosingDateValid(item.contractClosingDate)) {
          input_contractClosingDate.error = getString(R.string.error_candidate_invalid_contractClosingDate)
          focusView = input_contractClosingDate
          cancel = true
        }
        
        if (!isResumeFileNameValid(item.resumeFileName)) {
          input_resumeFileName.error = getString(R.string.error_candidate_invalid_resumeFileName)
          focusView = input_resumeFileName
          cancel = true
        }
        
        if (!isResumeFileLocationValid(item.resumeFileLocation)) {
          input_resumeFileLocation.error = getString(R.string.error_candidate_invalid_resumeFileLocation)
          focusView = input_resumeFileLocation
          cancel = true
        }
        
        if (!isResumeFileUploadedOnValid(item.resumeFileUploadedOn)) {
          input_resumeFileUploadedOn.error = getString(R.string.error_candidate_invalid_resumeFileUploadedOn)
          focusView = input_resumeFileUploadedOn
          cancel = true
        }
        

        if (cancel) {
            focusView?.requestFocus()
        } else {
            showProgress(true)
            isSaving=true

            val service = getCore().candidateService()

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

    private fun onSaveSuccess(CandidateDto: CandidateDto) {
        isSaving=false
        finish()
    }
    

    private fun isFirstNameValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isLastNameValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isPhoneNumberValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isEmailValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isDateOfBirthValid(field: LocalDate?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isGenderValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isPassportNumberValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isWorkAuthorisationCategoryValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isNationalityValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isWorkAuthorisationValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isValidityValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isExperienceLevelValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isTotalExperienceInYearsValid(field: Int?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isTotalExperienceInMonthsValid(field: Int?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isCurrentSalaryCurrencyValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isCurrentSalaryValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isCurrentSalaryTimeUnitValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isSkillTagsValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isResumeTitleValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isProfileSummaryValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isOpenForPhoneCallValid(field: Boolean?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isOpenForEmailCommunicationValid(field: Boolean?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isIsInContractValid(field: Boolean?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isContractClosingDateValid(field: LocalDate?): Boolean {
        //TODO: Replace this with your own logic
        
        return field != null
        
    }
    

    private fun isResumeFileNameValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isResumeFileLocationValid(field: String?): Boolean {
        //TODO: Replace this with your own logic
        
        return !TextUtils.isEmpty(field)
        
    }
    

    private fun isResumeFileUploadedOnValid(field: LocalDate?): Boolean {
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
