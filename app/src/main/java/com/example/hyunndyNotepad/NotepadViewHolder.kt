package com.example.hyunndyNotepad

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hyunndyNotepad.MemoItem as RecyclerItem


public class NotepadAdapter(val itemClick: (RecyclerItem, Int) -> Unit)  : RecyclerView.Adapter<NotepadAdapter.NotePadViewHolder>()
{
    private var updateMemolist = arrayListOf<RecyclerItem>()

    public fun setMemolist(set:ArrayList<RecyclerItem>)
    {
        if(this.updateMemolist != null)
        {
            this.updateMemolist.clear()
            this.updateMemolist.addAll(set)
        }
       // else
       // {
       //     this.updateMemolist.clear()
       //     this.updateMemolist.addAll(set)
       // }

        notifyDataSetChanged()
    }


    // 뷰홀더
     inner class NotePadViewHolder(var memoItem:View, var itemClick: (RecyclerItem, Int) -> Unit) : RecyclerView.ViewHolder(memoItem) {
        val image = memoItem?.findViewById<ImageView>(R.id.imageView2)
        val title = memoItem?.findViewById<TextView>(R.id.textView)
        val desc = memoItem?.findViewById<TextView>(R.id.textView3)


        fun bind (Items : RecyclerItem)
        {
            if(Items.getThumbnail() != null)
            {
                var array = Items.getThumbnail()
                var Bitmap = BitmapFactory.decodeByteArray(array, 0, array?.size!!)

                image.setImageBitmap(Bitmap)
            }
            else
            {
                image.setImageBitmap(null)
            }

            title.text = Items.getTitle()
            desc.text = Items.getDesc()

            // 메모하나가 클릭됐을 때 처리할 일을 itemClick으로 설정한다.
            // (RecyclerItem) -> Unit에 대한 함수는 나중에 mainactivity.kt에서 작성한다.
            memoItem.setOnClickListener{ it ->
                itemClick(Items, adapterPosition)
            }
        }
    }

    // @onCreateViewHolder()
    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotePadViewHolder {

        //  var context = parent.context
        // Inflater : xml코드로 작성된 레이아웃을 view로 실체화시켜주는것.
        //  var inflater: LayoutInflater =
        //  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflater로 view 생성
        val memoview = LayoutInflater.from(parent.context).inflate(R.layout.notepad_item, parent, false)

        // view를 리턴.
        return NotePadViewHolder(memoview, itemClick)
    }

    // @onBindViewHolder()
    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    // 데이터를 뷰홀더에 바인딩.
    override fun onBindViewHolder(holder: NotePadViewHolder, position: Int) {

        Log.d("test1", "onBindViewHolder가 언제 불릴까?")
        holder.bind(updateMemolist[position])
    }

    // 전체 아이템 갯수 리턴
    override fun getItemCount(): Int {
        return updateMemolist.size
    }
}