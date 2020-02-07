## Patient health monitoring system - A reference implementation

### Overview:
Almost every individual has a fitbit or similar tracking device which tracks important health parameters like heart rate, quality of sleep and others. With these devices in place, buidling a patient monitoring system on top of the data captured has emerged and become important as well.

This project aims at building one such patient monitoring system with Apache Kafka and Apache Edgent. 

### Components:
This project is divided into three modules.

  1.Data generator - Generates patient health parameters for given patients

  2.Edge processsing - Edge level processing like anonymization and basic filtering. Achieved with the help of Apache Edgent.

  3.Stream processing - Filter the anamoly records based on the configured threshold for a specified time window. Save the anamoly records in RDBMS which could then be sent as alerts. Achieved with the help of Apache Kafka Streams and connect.

### Getting started
git clone https://github.com/vignesh093/PatientHealthMonitoring.git
