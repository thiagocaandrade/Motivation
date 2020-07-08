package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowId
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.infra.motivationConstants
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.channels.spi.AbstractSelectionKey

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter : Int = motivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar!= null){
            supportActionBar!!.hide()
        }

        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(motivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá, $name!"


        //Lógica Inicial de seleção
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imagemHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        val id = view.id

        val listFilter = listOf(R.id.imageAll, R.id.imagemHappy, R.id.imageMorning)

        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imagemHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = motivationConstants.PHRASEFILTER.ALL
            }
            R.id.imagemHappy -> {
                imagemHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = motivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = motivationConstants.PHRASEFILTER.MORNING
            }
        }

    }

    private fun handleNewPhrase() {

        textPhrase.text = Mock().getPhrase(mPhraseFilter)

    }


}