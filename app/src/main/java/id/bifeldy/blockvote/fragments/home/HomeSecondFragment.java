package id.bifeldy.blockvote.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import id.bifeldy.blockvote.R;

public class HomeSecondFragment extends Fragment {

    // Arguments from other fragment
    private String myArg = null;

    // UI elements
    private TextView textView;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize View
        textView = view.findViewById(R.id.textview_home_second);
        button = view.findViewById(R.id.button_home_second);

        // Get arguments from other fragment if any
        if (getArguments() != null) {
            myArg = HomeSecondFragmentArgs.fromBundle(getArguments()).getMyArg();
        }

        // -- Custom functions
        appRun();
    }

    public void appRun() {

        // Set TextView with data from arguments
        textView.setText(getString(R.string.hello_home_second, myArg));

        // Button onClick navigate to other fragment without passing data
        button.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_HomeSecondFragment_to_HomeFragment));

    }
}
