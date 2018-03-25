package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPageFragment extends Fragment implements MyAsyncTask.Listeners {

    /*public NewsPageFragment() {
        // Required empty public constructor
    }*/

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static NewsPageFragment newInstance(){
        return (new NewsPageFragment());
    }



    //ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        this.startAsyncTask();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_page, container, false);

    }



    // ------


    // 3 - We create and start our AsyncTask

    private void startAsyncTask() {

        new MyAsyncTask(this).execute("http://192.168.1.15/test_php/mysql_connect.php");

    }


    // 2 - Override methods of callback

    @Override

    public void onPreExecute() {

        // 2.1 - We update our UI before task (starting ProgressBar)

        this.updateUIBeforeTask();

    }


    @Override

    public void doInBackground() { }


    @Override

    public void onPostExecute(String json) {

        // 2.2 - We update our UI before task (stopping ProgressBar)

        this.updateUIAfterTask(json);

    }


    // -----------------

    // UPDATE UI

    // -----------------


    public void updateUIBeforeTask(){

        //progressBar.setVisibility(View.VISIBLE);

    }


    public void updateUIAfterTask(String taskEnd){

        //progressBar.setVisibility(View.GONE);
        //Toast.makeText(super.getContext(), "Task is finally finished at : "+taskEnd+" !", Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) getView().findViewById(R.id.fragment_page_news_title);
        textView.setText(taskEnd);
    }



}
