package com.example.activityforresultdemo

import javax.xml.transform.Source

interface OnItemClickListener {
    fun navigateBySource(source: String, title: String)
}