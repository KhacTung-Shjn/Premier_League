package com.example.premier_league.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import kotlinx.android.synthetic.main.frament_setting.*


class SettingFragment : BaseFragment(), SettingContract.View {

    private var presenter: SettingPresenter? = null

    override val layoutResource: Int
        get() = R.layout.frament_setting

    override fun initData() {
        presenter = SettingPresenter(this)
        presenter?.start()
        val strArray1 = arrayOf("A", "B", "C")
        feedBack("Feedback about app Premier League !")
        shareWithFriend(
            "Introducing a great soccer app for you, would you like to try it out?", Uri.parse(
                "vcl"
            )
        )
        showAboutApp()
    }

    override fun showAboutApp() {
        textAboutApp.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("About app")
            builder.setMessage("Application developed by H&T \nVersion 1.0")
            //Nút Cancel
            builder.setNegativeButton("OK"
            ) { dialog, _ -> dialog.cancel() }
            val al: AlertDialog = builder.create()
            al.show()
        }
    }

    override fun shareWithFriend(message: String, attachment: Uri) {
        textShareFriend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:0369696969");  // This ensures only SMS apps respond
            intent.putExtra("sms_body", message);
            intent.putExtra(Intent.EXTRA_STREAM, attachment);
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            }
        }
    }

    override fun feedBack(subject: String?) {
        textFeedback.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:hoan9x21@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            }
        }
    }

    override fun changeLanguage() {

    }

}
