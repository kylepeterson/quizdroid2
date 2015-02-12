package quizdroid.kylep9.washington.edu.quizdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopicFragment extends Fragment {


    public static TopicFragment newInstance(String topicName, Question[] questions, String description) {
        Log.i("duba", "newInstance of fragment entered");
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString("topic-name", topicName);
        args.putSerializable("questions", questions);
        args.putString("description", description);
        fragment.setArguments(args);
        Log.i("duba", "newInstance of fragment return");

        return fragment;
    }

    public TopicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("duba", "Create of fragment entered");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("duba", "onCreateView of fragment entered");
        View rootView = inflater.inflate(R.layout.fragment_topic, container, false);
        Bundle args = getArguments();
        Log.i("duba", "args of fragment retrieved from bundle");
        //TextView topicName = (TextView) rootView.findViewById(R.id.topic);
        //Log.i("duba", "topic name grabbed: " + topicName);
        //topicName.setText(args.getString("topic-name"));
        //Log.i("duba", "topic name set");

        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(args.getString("topic-name"));
        Log.i("duba", "desc set");

      //  Button begin = (Button) rootView.findViewById(R.id.begin);
//        Log.i("duba", "button grabbed");

  //      begin.setOnClickListener(new View.OnClickListener() {
    //        @Override
      //      public void onClick(View v) {
        //        Log.i("duba", "in click handler");
//
  //          }
    //    });
      //  Log.i("duba", "onCreateView complete: " + rootView);
        return rootView;
    }
}
