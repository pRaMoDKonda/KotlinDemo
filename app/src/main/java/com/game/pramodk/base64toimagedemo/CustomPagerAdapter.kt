package com.game.pramodk.base64toimagedemo

/**
 * Created by siddheshn on 7/3/18.
 */
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.view.PagerAdapter
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.image_display_layout.view.*
import java.util.ArrayList

class CustomPagerAdapter(private val mContext: Context, var encodedBase64List :  ArrayList<String>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.image_display_layout, collection, false) as ViewGroup
        collection.addView(layout)

        val decodedBase64 = Base64.decode(encodedBase64List[position], Base64.NO_WRAP)
        val decodedBitmap = BitmapFactory.decodeByteArray(decodedBase64, 0, decodedBase64.size)
        if (null != decodedBitmap) {
            layout.ivDisplayImage.setImageBitmap(decodedBitmap)
        } else{
            layout.ivDisplayImage.setImageResource(R.drawable.ic_error)
        }
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return encodedBase64List.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.getString(R.string.app_name)
    }

}