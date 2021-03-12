package org.apache.wicket;
import org.apache.wicket.markup.html.WebPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 8073729955563770821L;

    private FormComponent months;
    private FormComponent totalAmount;
    private FormComponent percent;
    private FormComponent day;
    private FormComponent month;
    private FormComponent year;
    private ListView listview;

    public HomePage() {
        Form form = new Form("form");
        day = new TextField("day", new Model<Integer>(), Integer.class).setRequired(true);
        day.add(new RangeValidator<>(1, 15));
        form.add(day);

        month = new TextField("month", new Model<Integer>(), Integer.class).setRequired(true);
        month.add(new RangeValidator<>(1,12));
        form.add(month);

        year= new TextField("year", new Model<Integer>(), Integer.class).setRequired(true);
        year.add(new RangeValidator<>(2000,2021));
        form.add(year);

        months = new TextField("months", new Model<Integer>(), Integer.class).setRequired(true);
        months.add(new RangeValidator<>(3,180));
        form.add(months);

        totalAmount = new TextField("amount", new Model<Integer>(), Integer.class).setRequired(true);
        totalAmount.add(new RangeValidator<>(2000,1000000000));
        form.add(totalAmount);

        percent = new TextField("percent", new Model<Integer>(), Integer.class).setRequired(true);
        percent.add(new RangeValidator<Integer>(0,100));
        form.add(percent);
        List<Record>records= new ArrayList<Record>();


        listview = new ListView("listview", records) {
            protected void populateItem(ListItem item) {

                Record record= (Record)item.getModelObject();
                item.add(new Label("order",record.getId() ));
                item.add(new Label("date", record.getDate()));
                item.add(new Label("numOfDays", record.getDays()));
                item.add(new Label("amountPerMonth", record.getAmountPerMonth()));
                item.add(new Label("percentPerMonth", record.getPercentPerMonth()));
                item.add(new Label("left", record.getLeft()));
                item.add(new Label("totalPerMonth", record.getTotalPerMonth()));


            }

        };



        form.add(new Button(("result")){


           public void onSubmit(){

                int d=(int)day.getModelObject();
                int m=(int)month.getModelObject();
                int y=(int)year.getModelObject();
                int ms=(int)months.getModelObject();
                int total=(int)totalAmount.getModelObject();
                int per=(int)percent.getModelObject();
                LocalDate date = LocalDate.of(y, m, d);
                int numOfDays;
               double amountPerMonth=(double)total/ms;
               double totalCopy=total;
                records.clear();
                listview.modelChanged();
                listview.removeAll();
                for(int i=1;i<=ms;i++){
                    numOfDays = date.lengthOfMonth();
                    double interest= totalCopy*(per/100.0/360*numOfDays);
                    totalCopy=totalCopy-amountPerMonth;
                    records.add(new Record(i,date.toString(),numOfDays,String.format("%.2f",amountPerMonth),
                            String.format("%.2f",interest),String.format("%.2f",totalCopy),String.format("%.2f",interest+amountPerMonth )));
                    date=date.plusMonths(1);

                }

           }

        });

        form.add(new Button("erase"){

            @Override
            public void onSubmit(){

                day.setModelObject(null);
                month.setModelObject(null);
                year.setModelObject(null);
                months.setModelObject(null);
                totalAmount.setModelObject(null);
                percent.setModelObject(null);
                records.clear();

            }
        });

        listview.setReuseItems(true);
        add(form);
        add(listview);

    }





}

