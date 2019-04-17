package com.tekraze.kotlinTest.core.data.job

import com.tekraze.kotlinTest.core.cache.CombinedCache

class JobService(private val resource: JobRestResource, private val cache: CombinedCache) {

    companion object {
        private const val CACHE_KEY = "get:job:list"
    }

    fun readList(useCache: Boolean, success: (List<JobDto>) -> Unit, error: (statusCode: Int, response: String) -> Unit) : List<JobDto>{

        var items : Array<JobDto>? = null
        if(useCache) {
            items = cache.load(CACHE_KEY, java.lang.reflect.Array.newInstance(JobDto::class.java, 0).javaClass) as? Array<JobDto>?
        }
        resource.readList({list -> cache.save(CACHE_KEY,list); success(list) },error)

        if(items!=null){
            return items.toList()
        }
        return ArrayList()
    }
    fun create(item: JobDto, success: (JobDto) -> Unit, error: (statusCode: Int, response: String) -> Unit){
      resource.save(item,success,error)
    }
    fun update(item: JobDto, success: (JobDto) -> Unit, error: (statusCode: Int, response: String) -> Unit){
      resource.update(item,success,error)
    }
    fun delete(id: Long?, success: () -> Unit, error: (statusCode: Int, response: String) -> Unit) {
      resource.delete(id?:0, success, error)
    }
}
