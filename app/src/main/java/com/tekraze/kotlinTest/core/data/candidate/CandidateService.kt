package com.tekraze.kotlinTest.core.data.candidate

import com.tekraze.kotlinTest.core.cache.CombinedCache

class CandidateService(private val resource: CandidateRestResource, private val cache: CombinedCache) {

    companion object {
        private const val CACHE_KEY = "get:candidate:list"
    }

    fun readList(useCache: Boolean, success: (List<CandidateDto>) -> Unit, error: (statusCode: Int, response: String) -> Unit) : List<CandidateDto>{

        var items : Array<CandidateDto>? = null
        if(useCache) {
            items = cache.load(CACHE_KEY, java.lang.reflect.Array.newInstance(CandidateDto::class.java, 0).javaClass) as? Array<CandidateDto>?
        }
        resource.readList({list -> cache.save(CACHE_KEY,list); success(list) },error)

        if(items!=null){
            return items.toList()
        }
        return ArrayList()
    }
    fun create(item: CandidateDto, success: (CandidateDto) -> Unit, error: (statusCode: Int, response: String) -> Unit){
      resource.save(item,success,error)
    }
    fun update(item: CandidateDto, success: (CandidateDto) -> Unit, error: (statusCode: Int, response: String) -> Unit){
      resource.update(item,success,error)
    }
    fun delete(id: Long?, success: () -> Unit, error: (statusCode: Int, response: String) -> Unit) {
      resource.delete(id?:0, success, error)
    }
}
