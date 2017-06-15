package com.example.administrator.test.Presenter.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.Model.QT;
import com.example.administrator.test.Presenter.Fragment.UpdateFragment.Update_QT_Fragment;
import com.example.administrator.test.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class QT_Adapter extends RecyclerView.Adapter<QT_Adapter.QT_Holder> {

    List<QT> datas;
    FragmentManager manager;

    public QT_Adapter(List<QT> datas, FragmentManager manager){
        this.datas = datas;
        this.manager = manager;
    }

    @Override
    public QT_Adapter.QT_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item_qt, parent, false);
        return new QT_Holder(view);
    }

    @Override
    public void onBindViewHolder(QT_Adapter.QT_Holder holder, int position) {
        QT qt = datas.get(position);
        holder.setNo(position+1);
        holder.setTitle(qt.getQt());
        holder.setDate(sdf(qt.getDate()));
        holder.setId(qt.getId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    class QT_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // 위젯
        TextView txt_item_no, txt_item_title, txt_item_date;
        View convertView;
        // 받아오는 값
        int id;


        // 0. 생성자
        public QT_Holder(View convertView){
            super(convertView);
            this.convertView = convertView;
            convertView.setOnClickListener(this);
            findAddress();
        }

        // 1. findViewById -- 좀 귀찮지만 일단 함수로 빼냈다
        public void findAddress(){
            txt_item_no = (TextView) convertView.findViewById(R.id.txt_item_no);
            txt_item_title = (TextView) convertView.findViewById(R.id.txt_word_item_title);
            txt_item_date = (TextView) convertView.findViewById(R.id.txt_item_date);
        }

        // 2. 리스너
        public void onClick(View view){
            Update_QT_Fragment fragment = Update_QT_Fragment.newInstance(id);
            manager.beginTransaction().addToBackStack("qtlistback").add(R.id.fragment_container, fragment).commit();

        }

        // 3. set 함수
        public void setNo(int number){
            txt_item_no.setText(number+"");
        }

        public void setTitle(String title){
            txt_item_title.setText(title);
        }

        public void setDate(String date){
            txt_item_date.setText(date);
        }

        public void setId(int id) {
            this.id = id;
        }

    }

}
