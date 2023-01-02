package com.example.skycoreassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.skycoreassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        init()

    }

    private fun init(){

       mBinding.radiusSb.progress = 100
       mBinding.radiusSb.max = 5000
        mBinding.radiusSb.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
           //  getData(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                getData(p0?.progress)
            }
        })
    }
    private fun getData(num:Int?){
        Toast.makeText(this, num.toString(), Toast.LENGTH_SHORT).show()
    }
}