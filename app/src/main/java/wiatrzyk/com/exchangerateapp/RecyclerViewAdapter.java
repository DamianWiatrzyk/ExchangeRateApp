package wiatrzyk.com.exchangerateapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {



    private ArrayList<ListItem> mItems = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<ListItem> items) {
        mContext=context;
        mItems=items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        ListItem item = mItems.get(i);

        viewHolder.textKey.setText(item.getmKey());
        viewHolder.textValue.setText(item.getmValue());

        if(item.getmType()==0) {
            viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("item_key", mItems.get(i).getmKey());
                    intent.putExtra("item_value", mItems.get(i).getmValue());
                    intent.putExtra("item_date", mItems.get(i).getmDate());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textKey, textValue;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textKey = itemView.findViewById(R.id.text_View_Key);
            textValue = itemView.findViewById(R.id.text_View_Value);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
