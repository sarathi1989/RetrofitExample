package com.da.retrofitexample.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.da.retrofitexample.R
import com.da.retrofitexample.model.Hero
import com.da.retrofitexample.model.HeroEntity
import com.da.retrofitexample.viewmodel.MainActivityViewModelDao
import com.squareup.picasso.Picasso

class RecyclewViewItem(
    //var herokoin: HeroEntity? ,
    val contxt: Context?,
    val heroList: List<HeroEntity>?,
    val mainActivityViewModelDao: MainActivityViewModelDao) : RecyclerView.Adapter<RecyclewViewItem.CustomViewHolder>() {

    private val picasso = Picasso.get()

    class CustomViewHolder(v : View) : RecyclerView.ViewHolder(v){


        //val textView : TextView = v.findViewById(R.id.textView)
        val imageView : ImageView = v.findViewById(R.id.imgView)
        val cardView : CardView = v.findViewById(R.id.cardView)

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclewViewItem.CustomViewHolder {

           val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
            return CustomViewHolder(v)

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            //Toast.makeText(this,"hero koin "+herokoin.TEXT, Toast.LENGTH_SHORT).show()


           // herokoin = heroList!!.get(position)
            //val heroEntity = heroList!!.get(position)
           // holder.textView.text =heroList!!.get(position).name

            picasso.load(heroList!!.get(position).imageurl)
                .into(holder.imageView)








          //  holder.textView.text = heroList!!.get(position).getName()
           // holder.textView.text = herokoin.getName() + "confirm"
            holder.cardView.setOnClickListener{

                //Toast.makeText(contxt,"value => "+heroList!!.get(position).getName(), Toast.LENGTH_LONG).show()
                Toast.makeText(contxt,"value => "+ heroList!!.get(position).name,Toast.LENGTH_LONG).show()
            }



            holder.cardView.setOnLongClickListener {
               // val value = heroList!!.get(position).value
                val value = heroList!!.get(position).name

                mainActivityViewModelDao.delete(value)

                return@setOnLongClickListener false
            }
            // val value = heroList.get(position).value


        }

        override fun getItemCount(): Int {
            return heroList!!.size
        }


    }