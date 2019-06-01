package com.example.chatting


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.chatting.constdata.CHOOSE_PHOTO
import com.example.chatting.databinding.FragmentPersonalBinding
import com.example.chatting.room.UriBean
import com.example.chatting.viewmodel.PersonalViewModel

class PersonalFragment : Fragment() {

    private lateinit var binding:FragmentPersonalBinding
    private lateinit var viewModel: PersonalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPersonalBinding>(inflater,R.layout.fragment_personal, container, false)
        viewModel = ViewModelProviders.of(this).get(PersonalViewModel()::class.java)
        init()
        return binding.root
    }

    fun init(){
        binding.flRemove.setOnClickListener {
            viewModel.removeNews(requireContext())
        }
        binding.civUserHead.setOnClickListener {
            selectImageFromAlbum()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (data?.data!=null){
                initAvatar(data.data!!)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun selectImageFromAlbum(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        }else{
            openAlbum()
        }
    }

    private fun openAlbum(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOOSE_PHOTO)
    }

    private fun initAvatar(uri: Uri){
        Glide.with(this).load(uri).into(binding.civUserHead)
        val uriBean = UriBean( uri.path!!)
        viewModel.insertUri(requireContext(),uriBean)
    }
}
