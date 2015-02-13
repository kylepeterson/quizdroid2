// Kyle Peterson, INFO 498, QuizDroid
// Home page
package quizdroid.kylep9.washington.edu.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent nextActivity = new Intent(MainActivity.this, QuestionActivity.class);
                String[] a1 = {"1 Pi", "2 Pi", "2 Pie", "4 Pi"};
                Question q1 = new Question("How many pis are in a circle?", a1, 1);
                String[] a2 = {"1", "42", "4", "2"};
                Question q2 = new Question("2 + 2", a2, 2);
                Question[] qs = {q1, q2};
                QuestionList questions = new QuestionList(qs);

                // place extras onto intent
                nextActivity.putExtra("page-type", "topic");
                nextActivity.putExtra("topic-name", "Math");
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("description", "Mathematics is the science that deals with the logic of shape, quantity and arrangement.");
                startActivity(nextActivity);
                finish();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent nextActivity = new Intent(MainActivity.this, QuestionActivity.class);
                String[] a1 = {"Wires and cables", "the shock", "Electrons and protons", "I do not know"};
                Question q1 = new Question("What is electricity?", a1, 2);
                String[] a2 = {"gravity man", "42", "apple man", "yes"};
                Question q2 = new Question("Who is newton", a2, 0);
                Question[] qs = {q1, q2};
                QuestionList questions = new QuestionList(qs);


                // place extras onto intent
                nextActivity.putExtra("page-type", "topic");
                nextActivity.putExtra("topic-name", "Physics");
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("description", "Physics is the study of matter, energy, and the interaction between them");
                startActivity(nextActivity);
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent nextActivity = new Intent(MainActivity.this, QuestionActivity.class);
                String[] a1 = {"man with the webs", "wrestler hero", "sports guy", "gunslinger"};
                Question q1 = new Question("Who is Spiderman?", a1, 0);
                String[] a2 = {"apple man", "Ltan See", "drawing man", "i can not say"};
                Question q2 = new Question("Who is Stan Lee", a2, 2);
                String[] a3 = {"Your Tiny Brother", "man with no fear", "The man of iron", "Me"};
                Question q3 = new Question("Who is Tony Stark?", a3, 2);
                String[] a4 = {"My Dad", "Your Dad", "Our Dad", "I dont know my dad"};
                Question q4 = new Question("Who is Professor X?", a4, 1);
                Question[] qs = {q1, q2, q3, q4};
                QuestionList questions = new QuestionList(qs);


                // place extras onto intent
                nextActivity.putExtra("page-type", "topic");
                nextActivity.putExtra("topic-name", "Marvel Superheroes");
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("description", "Marvel is is an American publisher of comic books and related media.");
                startActivity(nextActivity);
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
