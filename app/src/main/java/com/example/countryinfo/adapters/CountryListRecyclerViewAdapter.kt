package com.example.countryinfo.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.example.countryinfo.R
import com.example.countryinfo.databinding.CountryListRecycleritemBinding
import com.example.countryinfo.interfaces.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.country_list_recycleritem.view.*


class CountryListRecyclerViewAdapter(
    countryNameAL: ArrayList<String>,
    countryPopAL: ArrayList<String>,
    countryFlagAL: ArrayList<String>,
    context: Context,
    recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<CountryListRecyclerViewAdapter.CountryListRecyclerViewHolder>() {

    private var countryNameAl: ArrayList<String> = countryNameAL
    private var countryPopAl: ArrayList<String> = countryPopAL
    private var countryFlagAl: ArrayList<String> = countryFlagAL
    private var mContext: Context = context
    private var recyclerViewItemClickListener1 = recyclerViewItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListRecyclerViewAdapter.CountryListRecyclerViewHolder {
        val countryListRecycleritemBinding: CountryListRecycleritemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.country_list_recycleritem,
                parent,
                false
            )
        return CountryListRecyclerViewHolder(countryListRecycleritemBinding)
    }

    override fun getItemCount(): Int {
        return countryNameAl.size
    }

    override fun onBindViewHolder(
        holder: CountryListRecyclerViewAdapter.CountryListRecyclerViewHolder,
        position: Int
    ) {

        holder.countryListRecycleritemBinding.countryName = countryNameAl[position]
        holder.countryListRecycleritemBinding.countryPopulation = countryPopAl[position]
        holder.countryListRecycleritemBinding.countryRecRL.setOnClickListener { recyclerViewItemClickListener1.onItemClicked(position) }
        SvgLoader.pluck()
            .with(mContext as Activity?)
            .load(countryFlagAl[position], holder.itemView.countryFlagIv);

    }


    class CountryListRecyclerViewHolder(countryListRecItemBinding: CountryListRecycleritemBinding) :
        RecyclerView.ViewHolder(countryListRecItemBinding.root) {
        var countryListRecycleritemBinding: CountryListRecycleritemBinding =
            countryListRecItemBinding
    }


    fun updateRecyclerView(
        countryNameAL: ArrayList<String>,
        countryPopAL: ArrayList<String>,
        countryFlagAL: ArrayList<String>
    ) {
        countryNameAl = countryNameAL
        countryPopAl = countryPopAL
        countryFlagAl = countryFlagAL
        notifyDataSetChanged()
    }


}