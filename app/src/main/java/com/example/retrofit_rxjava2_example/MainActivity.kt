package com.example.retrofit_rxjava2_example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.retrofit_rxjava2_example.adapter.DataAdapter
import com.example.retrofit_rxjava2_example.model.Android
import com.example.retrofit_rxjava2_example.retrofit2.DataClient
import com.example.retrofit_rxjava2_example.retrofit2.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mAndroidArrayList: java.util.ArrayList<Android>? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mAdapter: DataAdapter? = null
    private var dataClient: DataClient  = RetrofitClient.getClient()?.create(DataClient::class.java)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadJson()
    }

    private fun initRecyclerView() {
        rvData.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        rvData.layoutManager=layoutManager

    }

    fun loadJson(){
        mCompositeDisposable?.add(
            dataClient.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError))
    }


    private fun handleResponse(androidList: List<Android>) {
        mAndroidArrayList = ArrayList(androidList)
        mAdapter = DataAdapter(mAndroidArrayList!!)
        rvData.setAdapter(mAdapter)
    }

    private fun handleError(error: Throwable) {

        Toast.makeText(this, "Error " + error.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
