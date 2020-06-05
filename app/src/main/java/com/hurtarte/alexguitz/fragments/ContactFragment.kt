package com.hurtarte.alexguitz.fragments

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.hurtarte.alexguitz.R
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_contact.view.*



/**
 * A simple [Fragment] subclass.
 */
class ContactFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootview =inflater.inflate(R.layout.fragment_contact,container,false)


        rootview.facebookProfile.setOnClickListener {

            gotoFaceBookPage("1260623390")
            }

        rootview.twitterProfile.setOnClickListener {
            gotoTwitter("alexguitz")
        }

        rootview.sendEmail.setOnClickListener {
            sendEmail()
        }

        rootview.linkedInProfile.setOnClickListener {
            gotoLinkedIn("alexanderguitz")
        }




        return rootview
    }

    private fun gotoFaceBookPage(id: String){
        try {
            val facebookIntent: Intent = Uri.parse("fb://profile/$id").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(facebookIntent)

        }catch (e : ActivityNotFoundException){
            val facebookIntent: Intent = Uri.parse("https://www.facebook.com/$id").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(facebookIntent)

        }
    }

    private fun sendEmail(){
       val  eMailIntent : Intent = Intent(Intent.ACTION_SEND)
        eMailIntent.setType("text/plain")
        eMailIntent.putExtra(Intent.EXTRA_EMAIL,arrayOf("hello@alexguitz.com"))

        startActivity(eMailIntent)

    }


    private fun gotoTwitter(user: String){
        try {
            val goTwitter: Intent = Uri.parse("twitter://user?screen_name=$user").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(goTwitter)

        }catch (e : ActivityNotFoundException){
            val goTwitter: Intent = Uri.parse("https://twitter.com/#!/$user").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(goTwitter)

        }
    }

    private fun gotoLinkedIn(user: String){
        try {
            val goLinked: Intent = Uri.parse("linkedin://$user").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(goLinked)

        }catch (e : ActivityNotFoundException){
            val goLinked: Intent = Uri.parse("https://www.linkedin.com/in/$user/").let { webpage -> Intent(Intent.ACTION_VIEW,webpage) }
            startActivity(goLinked)

        }
    }


}
