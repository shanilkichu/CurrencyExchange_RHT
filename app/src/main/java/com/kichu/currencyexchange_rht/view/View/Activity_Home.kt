package com.kichu.currencyexchange_rht.view.View

import android.R
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.acube.sobhareader.ApiServices.ApiInterface
import com.kichu.currencyexchange_rht.databinding.LayoutHomeBinding
import com.kichu.currencyexchange_rht.view.Api.MainRepository
import com.kichu.currencyexchange_rht.view.Db.AppDatabase
import com.kichu.currencyexchange_rht.view.Db.CountryRateDB
import com.kichu.currencyexchange_rht.view.Viewmodel.MainViewModel
import com.kichu.currencyexchange_rht.view.model.MyViewModelFactory
import com.kichu.currencyexchange_rht.view.model.SpinnerData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class Activity_Home :AppCompatActivity(){

    lateinit var binding: LayoutHomeBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = ApiInterface.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = LayoutHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        viewModel.errorMessage.observe(this, Observer {
            Log.d("ERR DATA", "errorMessage: $it")
        })

        viewModel.getallCurrency()
        viewModel.currencyList.observe(this, Observer {

            Log.d("ERR DATA", "list: ${it.getAsJsonObject("rates")}")

            try {
                // Create the original JSON object
                val originalObject = JSONObject(it.getAsJsonObject("rates").toString())

                // Create a new JSON array
                val newArray = JSONArray()

                // Loop through each key-value pair in the original object
                val keys = originalObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val value = originalObject.getString(key)

                    // Create a new JSON object for each key-value pair
                    val obj = JSONObject()
                    obj.put(key, value)

                    // Add the new object to the array
                    newArray.put(obj)
                }

                // Convert the array to a string
                Log.d("ERR DATA", "list1: ${newArray}")
                val newArrayString = newArray.toString()
                Log.d("ERR DATA", "list2: ${newArrayString}")
                val jsonArray = JSONArray(newArrayString)
                val CurrencyList = ArrayList<SpinnerData>()
                for (i in 0 until jsonArray.length()) {
                    val keys = jsonArray.getJSONObject(i).keys()
                    val jsonObject =jsonArray.getJSONObject(i)

                    while (keys.hasNext()) {

                        val key = keys.next()
                        val usdValue = jsonObject.getString(key)

                        Log.d("ERR DATA", "list4: ${usdValue}")
                        Log.d("ERR DATA", "list3: ${key}")
                        val spinnerData = SpinnerData(key,usdValue)
                        Thread{
                            val rateValues=CountryRateDB(key,usdValue)
                            AppDatabase.getInstance(applicationContext).countryRateDao().insertAll(rateValues)
                            CurrencyList.add(spinnerData)
                        }.start()



                    }
                }
                SearchSpinner()


            } catch (e: JSONException) {
                e.printStackTrace()
            }

        })

    }

    private fun SearchSpinner() {
        val ListCurrency = ArrayList<String>()
        val ListRate = ArrayList<String>()
        var SelectedRate = String()
        ListCurrency.clear()
        ListRate.clear()
        AppDatabase.getInstance(applicationContext).countryRateDao().all.observe(
            this
        ) { delivery_reciept_dbs ->

            for (i in 0 until delivery_reciept_dbs.size) {
                ListCurrency.add(delivery_reciept_dbs.get(i).countryName)
                ListRate.add(delivery_reciept_dbs.get(i).rate)
            }

            val searchmethod =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, ListCurrency)
            searchmethod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFrom!!.adapter = searchmethod
            binding.spinnerTo!!.adapter = searchmethod
        }

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent?.getChildAt(0) as TextView).setTextColor(Color.WHITE)

                binding.textFrom.setText(ListRate.get(position))
            }

        }
        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent?.getChildAt(0) as TextView).setTextColor(Color.WHITE)

                binding.textTo.setText(ListRate.get(position))
                SelectedRate=ListRate.get(position)
            }

        }

        binding.textFrom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()){
                    val sum=((binding.textFrom.text.toString()).toDouble())*((binding.textTo.text.toString()).toDouble())
                    binding.textTo.setText(""+sum)
                }else{
                    binding.textTo.setText(""+SelectedRate)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



    }
}
