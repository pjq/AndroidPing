package pjq.me.ping;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.qiujuer.genius.material.MaterialButton;

/**
 * Created by pjq on 11/17/14.
 */
public class PingFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private MaterialButton resultButton;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PingFragment newInstance(int sectionNumber) {
        PingFragment fragment = new PingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ping, container, false);
        resultButton = (MaterialButton) rootView.findViewById(R.id.ping);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultButton.setText("PING...");
                ping();
            }
        });


        return rootView;
    }

    private void ping() {
        NetworkTool.ping(new NetworkTool.PingListener() {
            @Override
            public void onResult(final Object result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultButton.setText(result.toString());
                    }
                });
            }
        });
    }
}
