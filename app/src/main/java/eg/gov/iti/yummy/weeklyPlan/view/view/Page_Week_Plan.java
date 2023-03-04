package eg.gov.iti.yummy.weeklyPlan.view.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.db.ConcreteLocalSource;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.Repository;
import eg.gov.iti.yummy.model.WeekPlan;
import eg.gov.iti.yummy.network.API_Client;
import eg.gov.iti.yummy.weeklyPlan.view.presenter.WeekPlanPresenter;
import eg.gov.iti.yummy.weeklyPlan.view.presenter.WeekPlanPresenterInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Page_Week_Plan extends Fragment implements WeekPlanViewInterface,onWeeklyPlanClickListener {
    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7;
    WeeklyPlanAdapter adapter, adapter1, adapter2, adapter3, adapter4, adapter5, adapter6;
    ConcreteLocalSource cls;
    WeekPlanPresenterInterface weekPlanPresenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__week__plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView1 = view.findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);
        weekPlanPresenterInterface = new WeekPlanPresenter(Repository.getInstance(
                API_Client.getInstance(getContext()),
                ConcreteLocalSource.getInstance(getContext()), getContext()), this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        adapter = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView1.setAdapter(adapter);
        weekPlanPresenterInterface.getSatdayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter.setListWeek(weekPlans);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView1.setAdapter(adapter);

        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter1 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView2.setAdapter(adapter1);
        weekPlanPresenterInterface.getSundayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter1.setListWeek(weekPlans);
                        adapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView2.setAdapter(adapter1);

        recyclerView3 = view.findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        adapter2 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView3.setAdapter(adapter2);
        weekPlanPresenterInterface.getMondayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter2.setListWeek(weekPlans);
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView3.setAdapter(adapter2);

        recyclerView4 = view.findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        adapter3 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView4.setAdapter(adapter3);
        weekPlanPresenterInterface.getTuesdayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter3.setListWeek(weekPlans);
                        adapter3.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView4.setAdapter(adapter3);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        adapter4 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView5.setAdapter(adapter4);
        weekPlanPresenterInterface.getWeddayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter4.setListWeek(weekPlans);
                        adapter4.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView5.setAdapter(adapter4);

        recyclerView6 = view.findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        // input6 = new ArrayList<>();
        adapter5 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView6.setAdapter(adapter5);
        weekPlanPresenterInterface.getThursdayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter5.setListWeek(weekPlans);
                        adapter5.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView6.setAdapter(adapter5);

        recyclerView7 = view.findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getContext());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        //  input7 = new ArrayList<>();
        adapter6 = new WeeklyPlanAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView7.setAdapter(adapter6);
        weekPlanPresenterInterface.getFridayMeal().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        Log.e("kk", "succ:weekplan " );
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WeekPlan> weekPlans) {
                        adapter6.setListWeek(weekPlans);
                        adapter6.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("kk", "onError:weekplan " );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView7.setAdapter(adapter6);

        cls = ConcreteLocalSource.getInstance(getContext());
        SharedPreferences pref = getActivity().getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");

    }

    @Override
    public void deleteMealPlanOnClick(WeekPlan Meal) {
        deleteMeal(Meal);
    }

    @Override
    public void deleteMeal(WeekPlan mealDetail) {
        weekPlanPresenterInterface.deleteMeal(mealDetail);
    }
}