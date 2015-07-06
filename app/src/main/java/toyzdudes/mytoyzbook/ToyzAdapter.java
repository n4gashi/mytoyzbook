package toyzdudes.mytoyzbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import toyzdudes.mytoyzbook.workers.MTBRestClient;
import toyzdudes.mytoyzbook.workers.Toy;

public class ToyzAdapter extends ArrayAdapter<Toy> {

    private static class ViewHolder{
        SmartImageView image;
        TextView label;
    }

    public ToyzAdapter(Context context, ArrayList<Toy> toyz)
    {
        super(context, R.layout.item_toy, toyz);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get data item for this position
        Toy toy = getItem(position);

        // Existing view to be reused ? If no, inflate
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_toy, parent, false);
            viewHolder.image = (SmartImageView) convertView.findViewById(R.id.toy_image);
            viewHolder.label = (TextView) convertView.findViewById(R.id.toy_label);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        //viewHolder.image.setImageDrawable();
        if(toy.getImageURI() != null)
            viewHolder.image.setImageUrl(MTBRestClient.BASE_IMG_URL + toy.getImageURI());

        viewHolder.label.setText(toy.getTitre());

        // Return the completed view to render on screen
        return convertView;
    }


}
