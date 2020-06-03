package com.hurtarte.alexguitz.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.hurtarte.alexguitz.R
import com.hurtarte.alexguitz.adapters.DisplayAdapter
import com.hurtarte.alexguitz.model.Repository
import com.hurtarte.alexguitz.retrofit.GithubAPIService
import com.hurtarte.alexguitz.retrofit.RetrofitClient
import com.hurtarte.alexguitz.showErrorMessage
import kotlinx.android.synthetic.main.fragment_git_hub.*
import kotlinx.android.synthetic.main.fragment_git_hub.view.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class GitHubFragment : Fragment() {
    private lateinit var displayAdapter: DisplayAdapter

    private var browsedRepositories: List<Repository> = mutableListOf()

    private val githubAPIService: GithubAPIService by lazy {
        RetrofitClient.githubAPIService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_git_hub,container,false)

        /*val layoutManager = LinearLayoutManager(activity?.applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL


        recyclerView.layoutManager = layoutManager*/






        val githubUser ="natanguitz"

        fetchUserRepositories(githubUser)


        return rootView
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }*/

    private fun fetchUserRepositories(githubUser: String) {

        githubAPIService.searchRepositoriesByUser(githubUser).enqueue(object : Callback<List<Repository>> {

            override fun onResponse(call: Call<List<Repository>>?, response: Response<List<Repository>>) {

                if (response.isSuccessful) {
                    Log.i(TAG, "posts loaded from API " + response)

                    response.body()?.let {
                        browsedRepositories = it

                    }

                    if (browsedRepositories.isNotEmpty()) {
                        setupRecyclerView(browsedRepositories)
                    } else {

                        Toast.makeText(activity?.applicationContext,"No Items Found", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Log.i(TAG, "Error " + response)
                    //activity?.showErrorMessage(response.errorBody()!!)



                }
            }

            override fun onFailure(call: Call<List<Repository>>?, t: Throwable) {
                Toast.makeText(activity?.applicationContext,"Error fetching", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView(items: List<Repository>) {
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL




        recyclerView.layoutManager = layoutManager

        displayAdapter = activity?.applicationContext?.let { DisplayAdapter(it, items) }!!
        recyclerView.adapter = displayAdapter
    }

    companion object {

        //private val TAG = DisplayActivity::class.java.simpleName
        private val TAG = GitHubFragment::class.java.simpleName
    }

}
