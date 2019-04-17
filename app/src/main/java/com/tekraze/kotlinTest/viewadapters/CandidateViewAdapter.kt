package com.tekraze.kotlinTest.viewadapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.text.format.DateFormat
import com.tekraze.kotlinTest.core.l18n.EnumLocalization
import com.tekraze.kotlinTest.core.data.enum.*
import com.tekraze.kotlinTest.R
import com.tekraze.kotlinTest.core.data.candidate.CandidateDto

class CandidateViewAdapter(private val myDataset: List<CandidateDto>, private val editListener: (CandidateDto)->Unit, private val deleteListener: (CandidateDto)->Unit) :
        RecyclerView.Adapter<CandidateViewAdapter.ViewHolder>() {

    class ViewHolder(val parent: View, val editButton: View, val deleteButton: View
                      
                      ,val firstNameTextView: TextView
                      
                      ,val lastNameTextView: TextView
                      
                      ,val phoneNumberTextView: TextView
                      
                      ,val emailTextView: TextView
                      
                      ,val dateOfBirthTextView: TextView
                      
                      ,val genderTextView: TextView
                      
                      ,val passportNumberTextView: TextView
                      
                      ,val workAuthorisationCategoryTextView: TextView
                      
                      ,val nationalityTextView: TextView
                      
                      ,val workAuthorisationTextView: TextView
                      
                      ,val validityTextView: TextView
                      
                      ,val experienceLevelTextView: TextView
                      
                      ,val totalExperienceInYearsTextView: TextView
                      
                      ,val totalExperienceInMonthsTextView: TextView
                      
                      ,val currentSalaryCurrencyTextView: TextView
                      
                      ,val currentSalaryTextView: TextView
                      
                      ,val currentSalaryTimeUnitTextView: TextView
                      
                      ,val skillTagsTextView: TextView
                      
                      ,val resumeTitleTextView: TextView
                      
                      ,val profileSummaryTextView: TextView
                      
                      ,val openForPhoneCallTextView: TextView
                      
                      ,val openForEmailCommunicationTextView: TextView
                      
                      ,val isInContractTextView: TextView
                      
                      ,val contractClosingDateTextView: TextView
                      
                      ,val resumeFileNameTextView: TextView
                      
                      ,val resumeFileLocationTextView: TextView
                      
                      ,val resumeFileUploadedOnTextView: TextView
                      
    ) : RecyclerView.ViewHolder(parent)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CandidateViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_candidate_item, parent, false) as View

        return ViewHolder(view,view.findViewById(R.id.edit_button),view.findViewById(R.id.delete_button)
          
          , view.findViewById(R.id.tv_firstName)
          
          , view.findViewById(R.id.tv_lastName)
          
          , view.findViewById(R.id.tv_phoneNumber)
          
          , view.findViewById(R.id.tv_email)
          
          , view.findViewById(R.id.tv_dateOfBirth)
          
          , view.findViewById(R.id.tv_gender)
          
          , view.findViewById(R.id.tv_passportNumber)
          
          , view.findViewById(R.id.tv_workAuthorisationCategory)
          
          , view.findViewById(R.id.tv_nationality)
          
          , view.findViewById(R.id.tv_workAuthorisation)
          
          , view.findViewById(R.id.tv_validity)
          
          , view.findViewById(R.id.tv_experienceLevel)
          
          , view.findViewById(R.id.tv_totalExperienceInYears)
          
          , view.findViewById(R.id.tv_totalExperienceInMonths)
          
          , view.findViewById(R.id.tv_currentSalaryCurrency)
          
          , view.findViewById(R.id.tv_currentSalary)
          
          , view.findViewById(R.id.tv_currentSalaryTimeUnit)
          
          , view.findViewById(R.id.tv_skillTags)
          
          , view.findViewById(R.id.tv_resumeTitle)
          
          , view.findViewById(R.id.tv_profileSummary)
          
          , view.findViewById(R.id.tv_openForPhoneCall)
          
          , view.findViewById(R.id.tv_openForEmailCommunication)
          
          , view.findViewById(R.id.tv_isInContract)
          
          , view.findViewById(R.id.tv_contractClosingDate)
          
          , view.findViewById(R.id.tv_resumeFileName)
          
          , view.findViewById(R.id.tv_resumeFileLocation)
          
          , view.findViewById(R.id.tv_resumeFileUploadedOn)
          
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = myDataset[position]
        holder.editButton.setOnClickListener{view -> editListener(item) }
        holder.deleteButton.setOnClickListener{view -> deleteListener(item) }

        
        
          holder.firstNameTextView.text = item.firstName?:""
          
        
        
          holder.lastNameTextView.text = item.lastName?:""
          
        
        
          holder.phoneNumberTextView.text = item.phoneNumber?:""
          
        
        
          holder.emailTextView.text = item.email?:""
          
        
        
          holder.dateOfBirthTextView.text = EnumLocalization.localizeLocalDate(item.dateOfBirth,holder.parent.context)
          
        
        
          holder.genderTextView.text = item.gender?:""
          
        
        
          holder.passportNumberTextView.text = item.passportNumber?:""
          
        
        
          holder.workAuthorisationCategoryTextView.text = item.workAuthorisationCategory?:""
          
        
        
          holder.nationalityTextView.text = item.nationality?:""
          
        
        
          holder.workAuthorisationTextView.text = item.workAuthorisation?:""
          
        
        
          holder.validityTextView.text = item.validity?:""
          
        
        
          holder.experienceLevelTextView.text = item.experienceLevel?:""
          
        
        
          holder.totalExperienceInYearsTextView.text = item.totalExperienceInYears?.toString()?:""
          
        
        
          holder.totalExperienceInMonthsTextView.text = item.totalExperienceInMonths?.toString()?:""
          
        
        
          holder.currentSalaryCurrencyTextView.text = item.currentSalaryCurrency?:""
          
        
        
          holder.currentSalaryTextView.text = item.currentSalary?:""
          
        
        
          holder.currentSalaryTimeUnitTextView.text = item.currentSalaryTimeUnit?:""
          
        
        
          holder.skillTagsTextView.text = item.skillTags?:""
          
        
        
          holder.resumeTitleTextView.text = item.resumeTitle?:""
          
        
        
          holder.profileSummaryTextView.text = item.profileSummary?:""
          
        
        
          holder.openForPhoneCallTextView.text = item.openForPhoneCall?.toString()?:""
          
        
        
          holder.openForEmailCommunicationTextView.text = item.openForEmailCommunication?.toString()?:""
          
        
        
          holder.isInContractTextView.text = item.isInContract?.toString()?:""
          
        
        
          holder.contractClosingDateTextView.text = EnumLocalization.localizeLocalDate(item.contractClosingDate,holder.parent.context)
          
        
        
          holder.resumeFileNameTextView.text = item.resumeFileName?:""
          
        
        
          holder.resumeFileLocationTextView.text = item.resumeFileLocation?:""
          
        
        
          holder.resumeFileUploadedOnTextView.text = EnumLocalization.localizeLocalDate(item.resumeFileUploadedOn,holder.parent.context)
          
        
    }

    override fun getItemCount() = myDataset.size
}
