package com.example.retrivephonestorage.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment  {

   /* public static RecyclerView recyclerView;
    private FirstFragmentAdapter firstFragmentAdapter;
    private List<File> pdfList;
    private onPadfSelectListener listener;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       /* View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.xRecyclerviewFirstFragment);

        runtimePermission();*/
        return null;
    }

/*    private void runtimePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                displayPdf();

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                firstFragmentAdapter.notifyDataSetChanged();
            }
        }
    }

    private void displayPdf() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        pdfList = new ArrayList<>();
        pdfList.addAll(findPdf(Environment.getExternalStorageDirectory()));
        firstFragmentAdapter = new FirstFragmentAdapter(getContext(),pdfList);
        recyclerView.setAdapter(firstFragmentAdapter);
    }

    public ArrayList<File> findPdf(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        try {
            if (files.length > 0 || files != null){
                for (File singleFile: files){
                    if (singleFile.isDirectory() && !singleFile.isHidden()){
                        arrayList.addAll(findPdf(singleFile));
                    }
                    else {
                        if (singleFile.getName().endsWith(".pdf")){
                            arrayList.add(singleFile);
                        }
                    }
                }
            }
            else {
                Toast.makeText(requireContext(),"File not Found",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(requireContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
        return arrayList;
    }*/
}