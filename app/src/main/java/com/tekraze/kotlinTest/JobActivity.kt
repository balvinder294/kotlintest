package com.tekraze.kotlinTest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.tekraze.kotlinTest.core.data.job.JobDto
import com.tekraze.kotlinTest.viewadapters.JobViewAdapter

import kotlinx.android.synthetic.main.activity_job.*
import kotlinx.android.synthetic.main.content_job.*

class JobActivity : BaseActivity() {

    companion object {
        fun openIntent(from: Context) : Intent{
            return Intent(from,JobActivity::class.java)
        }
    }

    private val items = ArrayList<JobDto>()
    private lateinit var viewAdapter: JobViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
          startActivity(JobDetailActivity.newIntent(this))
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewManager = LinearLayoutManager(this)
        viewAdapter = JobViewAdapter(items, {item -> editItem(item) }, {item -> deleteItem(item) })

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        swiperefresh.setOnRefreshListener { refreshList() }
    }

    private fun deleteItem(item: JobDto) {
      AlertDialog.Builder(this)
        .setMessage(R.string.sure_to_delete)
        .setPositiveButton(R.string.delete) { dialogInterface, i ->
          getCore().jobService().delete(item.id,{ deleteSuccess() }, { code,error -> deleteError(error)})
        }
        .setNegativeButton(R.string.cancel,null)
        .show()
    }

    private fun deleteError(error: String) {
      Toast.makeText(this,R.string.delete_error,Toast.LENGTH_SHORT).show()
    }

    private fun deleteSuccess() {
      refreshList()
    }

    private fun editItem(item: JobDto) {
      startActivity(JobDetailActivity.editIntent(this,item))
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        swiperefresh.isRefreshing = true
        populateItems(
                getCore().jobService()
                        .readList(
                                true,
                                {list: List<JobDto> -> populateItems(list); dismissLoadingIndicator() },
                                { statusCode: Int, response: String -> showError(); dismissLoadingIndicator() }
                        ))
    }

    private fun dismissLoadingIndicator() {
        swiperefresh.isRefreshing = false
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.error_getting_items), Toast.LENGTH_SHORT).show()
    }

    private fun populateItems(list: List<JobDto>) {
        items.clear()
        items.addAll(list)
        viewAdapter.notifyDataSetChanged()
    }


}
