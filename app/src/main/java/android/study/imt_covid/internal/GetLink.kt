package android.study.imt_covid.internal

import android.text.method.LinkMovementMethod
import android.widget.TextView

 fun getLink(view: TextView?) {
    if (view != null) {
        view.movementMethod = LinkMovementMethod.getInstance()
    }
}