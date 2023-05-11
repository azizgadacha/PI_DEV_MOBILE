/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author Hend
 */
public class StatisticsForm extends Form {

    private final HomeForm homeForm;
    private final ArrayList<Integer> statistics;

    public StatisticsForm(HomeForm homeForm) {
        super("Statistics");
        this.homeForm = homeForm;
        this.statistics = new ArrayList<>();

        // Retrieve the statistics from the server
        fetchStatistics();
    }

    private void fetchStatistics() {
        ServiceReclamation service = new ServiceReclamation();
        statistics.addAll(service.getStatistics());

        // Add the statistics to the form
        addStatisticsToList();
    }

    private void addStatisticsToList() {
        // Create a list item for each statistic
        MultiButton totalReclamationsBtn = new MultiButton("Total Reclamations: " + statistics.get(0));
        MultiButton nonTraiteesBtn = new MultiButton("Reclamations Non Traitees: " + statistics.get(1));
        MultiButton traiteesBtn = new MultiButton("Reclamations Traitees: " + statistics.get(2));
        MultiButton enAttenteBtn = new MultiButton("Reclamations En Attente: " + statistics.get(3));

        // Add the list items to the form
        add(totalReclamationsBtn);
        add(nonTraiteesBtn);
        add(traiteesBtn);
        add(enAttenteBtn);
    }
}
