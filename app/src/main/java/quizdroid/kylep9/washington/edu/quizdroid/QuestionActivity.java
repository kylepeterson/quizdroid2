package quizdroid.kylep9.washington.edu.quizdroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if(savedInstanceState == null) {
            OverviewFragment topic = new OverviewFragment();
            topic.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().replace(R.id.container, topic).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class OverviewFragment extends Fragment {

        public OverviewFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_fragment, container, false);
            final Bundle args = getArguments();
            Log.i("duba", "args of fragment retrieved from bundle");
            TextView topicName = (TextView) rootView.findViewById(R.id.topic);
            Log.i("duba", "topic name grabbed: " + topicName);
            topicName.setText(args.getString("topic-name"));
            Log.i("duba", "topic name set");

            TextView description = (TextView) rootView.findViewById(R.id.description);
            description.setText(args.getString("description"));
            Log.i("duba", "desc set");

            Button begin = (Button) rootView.findViewById(R.id.begin);
            Log.i("duba", "button grabbed");

            begin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("duba", "in click handler");
                    QuestionFragment question = new QuestionFragment();

                    Bundle nextArgs = new Bundle();
                    nextArgs.putInt("correct", 0);
                    nextArgs.putSerializable("questions", args.getSerializable("questions"));
                    question.setArguments(nextArgs);
                    getFragmentManager().beginTransaction().replace(R.id.container, question).commit();
                }
            });
            Log.i("duba", "onCreateView complete: " + rootView);
            return rootView;
        }
    }

    public static class QuestionFragment extends Fragment {

        public QuestionFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
            Log.i("fragments", "infalted question view: " + rootView);

            final Bundle args = getArguments();
            Log.i("frag", "args for second fragment: " + args.getClass());
            final QuestionList qs =  (QuestionList) args.getSerializable("questions");
            final int currentQ = qs.getCurrentIndex();
            final Question cur = qs.getQuestion(currentQ);
            String[] answers = cur.getAnswers();
            final int correctIndex = cur.getCorrectIndex();
            final int correctAnswers = args.getInt("correct");
            String currentQText = cur.getQuestion();

            TextView questionText = (TextView) rootView.findViewById(R.id.question);
            questionText.setText(currentQText);

            RadioButton ans1 = (RadioButton) rootView.findViewById(R.id.ans1);
            RadioButton ans2 = (RadioButton) rootView.findViewById(R.id.ans2);
            RadioButton ans3 = (RadioButton) rootView.findViewById(R.id.ans3);
            RadioButton ans4 = (RadioButton) rootView.findViewById(R.id.ans4);

            ans1.setText(answers[0]);
            ans2.setText(answers[1]);
            ans3.setText(answers[2]);
            ans4.setText(answers[3]);

            Button submit = (Button)  rootView.findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("duba", "in click handler");
                    ResultFragment result = new ResultFragment();
                    RadioGroup group = (RadioGroup) rootView.findViewById(R.id.mathoptions);
                    int selectId = group.getCheckedRadioButtonId();
                    if(selectId != -1) {
                        Bundle nextArgs = new Bundle();
                        qs.increaseIndex();
                        nextArgs.putSerializable("questions", qs);

                        RadioButton selected = (RadioButton) rootView.findViewById(selectId);
                        String selectedAnswer = selected.getText() + "";
                        String rightAnswer = cur.getAnswers()[correctIndex];
                        if (rightAnswer.equals(selectedAnswer)) {
                            nextArgs.putInt("correct", correctAnswers + 1);
                        } else {
                            nextArgs.putInt("correct", correctAnswers);
                        }
                        nextArgs.putString("rightAnswer", rightAnswer);
                        nextArgs.putString("selectedAnswer", selectedAnswer);
                        result.setArguments(nextArgs);
                        getFragmentManager().beginTransaction().replace(R.id.container, result).commit();
                    }

                }
            });
            return rootView;
        }
    }

    public static class ResultFragment extends Fragment {

        public ResultFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.results_fragment, container, false);
            Log.i("fragments", "infalted results view: " + rootView.getClass());

            final Bundle args = getArguments();
            final QuestionList qs =  (QuestionList) args.getSerializable("questions");
            final int total = qs.getCurrentIndex();
            final int correct = args.getInt("correct");
            final String rightAnswer = args.getString("rightAnswer");
            final String selectedAnswer = args.getString("selectedAnswer");

            TextView correctBox = (TextView) rootView.findViewById(R.id.cor);
            TextView totalBox = (TextView) rootView.findViewById(R.id.totalNum);
            Button nextButton = (Button) rootView.findViewById(R.id.next);

            correctBox.setText("Chosen: " + selectedAnswer + "\nCorrect: " + rightAnswer);
            totalBox.setText(correct + " / " + total);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(total == qs.getQuestionList().size()) {
                        Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(nextActivity);
                        getActivity().finish();
                    } else {
                        QuestionFragment question = new QuestionFragment();
                        Bundle nextArgs = new Bundle();
                        nextArgs.putSerializable("questions", qs);
                        nextArgs.putInt("correct", correct);
                        question.setArguments(nextArgs);
                        getFragmentManager().beginTransaction().replace(R.id.container, question).commit();
                    }
                }
            });
            return rootView;
        }
    }
}

