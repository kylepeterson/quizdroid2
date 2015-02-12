// Kyle Peterson, INFO 498, QuizDroid
// Question Activity
package quizdroid.kylep9.washington.edu.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;


public class MathQuestion1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Duba", "question 1 entered");

        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_question1);

        final int count = intent.getExtras().getInt("count");
        final int correct = intent.getExtras().getInt("correct");
        final Object[] temp = (Object[]) intent.getExtras().get("questions");
        final Question[] questions = Arrays.copyOf(temp, temp.length, Question[].class);
        final int current = intent.getExtras().getInt("current");
        Log.i("Duba", "q1 extras retreived");

        Question toDisplay = questions[current];
        String question = toDisplay.getQuestion();
        String[] currentAs = toDisplay.getAnswers();
        final int rightAnswer = toDisplay.getCorrectIndex();

        ((TextView) findViewById(R.id.question)).setText(question);
        ((RadioButton) findViewById(R.id.ans1)).setText(currentAs[0]);
        ((RadioButton) findViewById(R.id.ans2)).setText(currentAs[1]);
        ((RadioButton) findViewById(R.id.ans3)).setText(currentAs[2]);
        ((RadioButton) findViewById(R.id.ans4)).setText(currentAs[3]);

        Button submit = (Button) findViewById(R.id.mathq1submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = (RadioGroup) findViewById(R.id.mathoptions);
                int selectId = group.getCheckedRadioButtonId();

                if(selectId != -1) {
                    Intent resultsActivity = new Intent(MathQuestion1.this, MathResultsActivity.class);
                    Log.i("Duba", "intent set up from q1: " + resultsActivity.toString());
                    resultsActivity.putExtra("count", count + 1);
                    resultsActivity.putExtra("current", current + 1);
                    resultsActivity.putExtra("questions", questions);

                    RadioButton selected = (RadioButton) findViewById(selectId);
                    String selectedAnswer = selected.getText() + "";
                    Log.i("Stew", "Right Answer " + questions[current].getAnswers()[rightAnswer] + " Selected Answer: " + selectedAnswer);
                    // Check if selected answer matches the correct one in the question object
                    if (questions[current].getAnswers()[rightAnswer].equals(selectedAnswer)) {
                        resultsActivity.putExtra("correct", correct + 1);
                        Log.i("Duba", "right answer");
                    } else {
                        resultsActivity.putExtra("correct", correct);
                        Log.i("Duba", "wrong answer");
                    }
                    Log.i("Duba", "starting activity: " + resultsActivity.toString());
                    startActivity(resultsActivity);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_math_question1, menu);
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
}
