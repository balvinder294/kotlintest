package com.tekraze.kotlinTest.core.data.job

import org.codehaus.jackson.annotate.JsonIgnoreProperties
import java.io.Serializable
import java.util.Date
import com.tekraze.kotlinTest.core.data.enum.*

@JsonIgnoreProperties(ignoreUnknown = true)
class JobDto : Serializable {

    var id: Long? = null

    
    var jobName: String? = null
    
    var companyName: String? = null
    
    var location: String? = null
    
    var positionType: String? = null
    
    var jobTags: Set&lt;String&gt;? = null
    
    var jobDescription: String? = null
    
    var applyUrl: String? = null
    
    var createdBy: String? = null
    
    var createdAt: LocalDate? = null
    
    var modifiedBy: String? = null
    
    var modifiedAt: LocalDate? = null
    
}
