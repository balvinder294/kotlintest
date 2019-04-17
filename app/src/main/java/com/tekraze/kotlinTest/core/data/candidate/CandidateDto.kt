package com.tekraze.kotlinTest.core.data.candidate

import org.codehaus.jackson.annotate.JsonIgnoreProperties
import java.io.Serializable
import java.util.Date
import com.tekraze.kotlinTest.core.data.enum.*

@JsonIgnoreProperties(ignoreUnknown = true)
class CandidateDto : Serializable {

    var id: Long? = null

    
    var firstName: String? = null
    
    var lastName: String? = null
    
    var phoneNumber: String? = null
    
    var email: String? = null
    
    var dateOfBirth: LocalDate? = null
    
    var gender: String? = null
    
    var passportNumber: String? = null
    
    var workAuthorisationCategory: String? = null
    
    var nationality: String? = null
    
    var workAuthorisation: String? = null
    
    var validity: String? = null
    
    var experienceLevel: String? = null
    
    var totalExperienceInYears: Int? = null
    
    var totalExperienceInMonths: Int? = null
    
    var currentSalaryCurrency: String? = null
    
    var currentSalary: String? = null
    
    var currentSalaryTimeUnit: String? = null
    
    var skillTags: String? = null
    
    var resumeTitle: String? = null
    
    var profileSummary: String? = null
    
    var openForPhoneCall: Boolean? = null
    
    var openForEmailCommunication: Boolean? = null
    
    var isInContract: Boolean? = null
    
    var contractClosingDate: LocalDate? = null
    
    var resumeFileName: String? = null
    
    var resumeFileLocation: String? = null
    
    var resumeFileUploadedOn: LocalDate? = null
    
}
