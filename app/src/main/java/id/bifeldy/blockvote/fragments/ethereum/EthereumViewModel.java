package id.bifeldy.blockvote.fragments.ethereum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EthereumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EthereumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ethereum fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}