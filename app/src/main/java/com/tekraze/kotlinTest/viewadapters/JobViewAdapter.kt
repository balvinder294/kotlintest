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
import com.tekraze.kotlinTest.core.data.job.JobDto

class JobViewAdapter(private val myDataset: List<JobDto>, private val editListener: (JobDto)->Unit, private val deleteListener: (JobDto)->Unit) :
        RecyclerView.Adapter<JobViewAdapter.ViewHolder>() {

    class ViewHolder(val parent: View, val editButton: View, val deleteButton: View
                      
                      ,val jobNameTextView: TextView
                      
                      ,val companyNameTextView: TextView
                      
                      ,val locationTextView: TextView
                      
                      ,val positionTypeTextView: TextView
                      
                      ,val jobTagsTextView: TextView
                      
                      ,val jobDescriptionTextView: TextView
                      
                      ,val applyUrlTextView: TextView
                      
                      ,val createdByTextView: TextView
                      
                      ,val createdAtTextView: TextView
                      
                      ,val modifiedByTextView: TextView
                      
                      ,val modifiedAtTextView: TextView
                      
    ) : RecyclerView.ViewHolder(parent)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): JobViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_job_item, parent, false) as View

        return ViewHolder(view,view.findViewById(R.id.edit_button),view.findViewById(R.id.delete_button)
          
          , view.findViewById(R.id.tv_jobName)
          
          , view.findViewById(R.id.tv_companyName)
          
          , view.findViewById(R.id.tv_location)
          
          , view.findViewById(R.id.tv_positionType)
          
          , view.findViewById(R.id.tv_jobTags)
          
          , view.findViewById(R.id.tv_jobDescription)
          
          , view.findViewById(R.id.tv_applyUrl)
          
          , view.findViewById(R.id.tv_createdBy)
          
          , view.findViewById(R.id.tv_createdAt)
          
          , view.findViewById(R.id.tv_modifiedBy)
          
          , view.findViewById(R.id.tv_modifiedAt)
          
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = myDataset[position]
        holder.editButton.setOnClickListener{view -> editListener(item) }
        holder.deleteButton.setOnClickListener{view -> deleteListener(item) }

        
        
          holder.jobNameTextView.text = item.jobName?:""
          
        
        
          holder.companyNameTextView.text = item.companyName?:""
          
        
        
          holder.locationTextView.text = item.location?:""
          
        
        
          holder.positionTypeTextView.text = item.positionType?:""
          
        
        
          holder.jobTagsTextView.text = EnumLocalization.localizeSet&lt;String&gt;(item.jobTags,holder.parent.context)
          
        
        
          holder.jobDescriptionTextView.text = item.jobDescription?:""
          
        
        
          holder.applyUrlTextView.text = item.applyUrl?:""
          
        
        
          holder.createdByTextView.text = item.createdBy?:""
          
        
        
          holder.createdAtTextView.text = EnumLocalization.localizeLocalDate(item.createdAt,holder.parent.context)
          
        
        
          holder.modifiedByTextView.text = item.modifiedBy?:""
          
        
        
          holder.modifiedAtTextView.text = EnumLocalization.localizeLocalDate(item.modifiedAt,holder.parent.context)
          
        
    }

    override fun getItemCount() = myDataset.size
}
