package id.bifeldy.blockvote.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import id.bifeldy.blockvote.R;

public class HomeFragment extends Fragment {

    // MVCs
    private HomeViewModel homeViewModel;

    // UI elements
    private TextView textView;
    private Button button;

    // Navigation
    private HomeFragmentDirections.ActionHomeFragmentToHomeSecondFragment action;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize View
        textView = view.findViewById(R.id.text_home);
        button = view.findViewById(R.id.button_home);

        // Set navigation with data
        action = HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment("From HomeFragment");

        // -- Custom functions
        appRun();
    }

    public void appRun() {

        // Set TextView with data from ViewModel
        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));

        // Button onClick navigate to other fragment with data as arguments
        button.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(action));

    }
}
