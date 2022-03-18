package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.config.AppProps;

import static ru.otus.spring.config.JobConfig.IMPORT_USER_JOB_NAME;
import static ru.otus.spring.config.JobConfig.INPUT_FILE_NAME;
import static ru.otus.spring.config.JobConfig.OUTPUT_FILE_NAME;

@ShellComponent
@RequiredArgsConstructor
public class BatchCommand {
    private final AppProps appProps;
    private final Job importUserJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    //http://localhost:8080/h2-console/

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addString(INPUT_FILE_NAME, appProps.getInputFile())
                .addString(OUTPUT_FILE_NAME, appProps.getOutputFile())
                .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(IMPORT_USER_JOB_NAME,
                INPUT_FILE_NAME + "=" + appProps.getInputFile() + "\n" +
                        OUTPUT_FILE_NAME + "=" + appProps.getOutputFile()
        );
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_USER_JOB_NAME));
    }
}
