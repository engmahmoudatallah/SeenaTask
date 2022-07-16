package com.seenatask.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seenatask.databinding.FragmentFullScreenImageListDialogBinding

// TODO: Customize parameter argument names
const val IMAGE_URL = "image_url"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    FullScreenImage.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class FullScreenImage : BottomSheetDialogFragment() {

    private var _binding: FragmentFullScreenImageListDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFullScreenImageListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnClose.setOnClickListener {
            dismiss()
        }


        Glide.with(requireContext()).load(arguments?.getString(IMAGE_URL).toString()).fitCenter()
            .into(binding.imvPicture)
    }


    companion object {

        // TODO: Customize parameters
        fun newInstance(imageUrl: String): FullScreenImage =
            FullScreenImage().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, imageUrl)
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}