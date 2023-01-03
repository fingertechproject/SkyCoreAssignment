package com.example.skycoreassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import com.example.skycoreassignment.databinding.ActivityMainBinding
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    var dataModel:Model? = null
    var list:List<Businesse>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        init()
      list = getData(3000).businesses
        mBinding.rvitem.adapter = SkyCoreAdapter(this,list!!)
    }

    private fun init(){

       mBinding.radiusSb.progress = 100
       mBinding.radiusSb.max = 5000
        mBinding.radiusSb.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
              list =   getData(p0?.progress).businesses
                mBinding.rvitem.adapter = SkyCoreAdapter(applicationContext, list!!)
            }
        })
    }
    private fun getData(num:Int?):Model{
        Toast.makeText(this, num.toString(), Toast.LENGTH_SHORT).show()
       val response = ApiFacade.getApiService().getData(num!!).execute()
        if (response.isSuccessful){
            dataModel = response.body()
            Log.d("SKY_CORE",dataModel.toString())
        }else if(response.errorBody()!=null){
            Log.d("SKY_CORE","${response.errorBody()}")
        }
        return dataModel!!
    }
}