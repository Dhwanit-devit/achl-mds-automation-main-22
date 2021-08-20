const report = require('multiple-cucumber-html-reporter');
const os = require('os');
const propertiesReader = require('properties-reader');
const report_properties = propertiesReader('./report.properties');
const app_properties = propertiesReader('../config/application.properties');

report.generate({
    jsonDir: report_properties.get("cucumber.json.dir"),
    reportPath: report_properties.get("generate.report.path"),
    metadata: {
        browser: {
            name: 'chrome',
            version: report_properties.get("browser.version")
        },
        device: os.platform(),
        platform: {
            name: getOS_Type(os.type),
            version: os.release()
        }
    },

    displayDuration: true,
    disableLog: true,
    pageFooter: " ",
    pageTitle: report_properties.get("report.title"),
    reportName: report_properties.get("report.name"),
    customData: {
        title: 'Project Specifications',
        data: [
            { label: 'Project', value: 'Limendo' },
            { label: 'Release', value: app_properties.get("base.url") },
            { label: 'Purpose', value: 'UI verification' },
            { label: 'Instance', value: app_properties.get("branch.name") },
            { label: 'Date', value: new Date().toLocaleString() },
        ]
    }
});


function getOS_Type(type) {
    if (type == 'Windows_NT') {
        return 'windows';
    } else if (type == 'Linux' || type == 'linux') {
        return 'linux'
    } else if (type == 'Darwin') {
        return 'osx';
    }
}
