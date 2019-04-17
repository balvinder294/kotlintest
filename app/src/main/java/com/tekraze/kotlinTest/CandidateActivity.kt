package com.tekraze.kotlinTest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.tekraze.kotlinTest.core.data.candidate.CandidateDto
import com.tekraze.kotlinTest.viewadapters.CandidateViewAdapter

import kotlinx.android.synthetic.main.activity_candidate.*
import kotlinx.android.synthetic.main.content_candidate.*

class CandidateActivity : BaseActivity() {

    companion object {
        fun openIntent(from: Context) : Intent{
            return Intent(from,CandidateActivity::class.java)
        }
    }

    private val items = ArrayList<CandidateDto>()
    private lateinit var viewAdapter: CandidateViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
          startActivity(CandidateDetailActivity.newIntent(this))
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewManager = LinearLayoutManager(this)
        viewAdapter = CandidateViewAdapter(items, {item -> editItem(item) }, {item -> deleteItem(item) })

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        swiperefresh.setOnRefreshListener { refreshList() }
    }

    private fun deleteItem(item: CandidateDto) {
      AlertDialog.Builder(this)
        .setMessage(R.string.sure_to_delete)
        .setPositiveButton(R.string.delete) { dialogInterface, i ->
          getCore().candidateService().delete(item.id,{ deleteSuccess() }, { code,error -> deleteError(error)})
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

    private fun editItem(item: CandidateDto) {
      startActivity(CandidateDetailActivity.editIntent(this,item))
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        swiperefresh.isRefreshing = true
        populateItems(
                getCore().candidateService()
                        .readList(
                                true,
                                {list: List<CandidateDto> -> populateItems(list); dismissLoadingIndicator() },
                                { statusCode: Int, response: String -> showError(); dismissLoadingIndicator() }
                        ))
    }

    private fun dismissLoadingIndicator() {
        swiperefresh.isRefreshing = false
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.error_getting_items), Toast.LENGTH_SHORT).show()
    }

    private fun populateItems(list: List<CandidateDto>) {
        items.clear()
        items.addAll(list)
        viewAdapter.notifyDataSetChanged()
    }


}
