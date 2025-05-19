from kafka import KafkaProducer
import time

producer = KafkaProducer(bootstrap_servers='localhost:9092')

with open("logs.log", "r") as fichier:
    for ligne in fichier:
        ligne = ligne.strip()
        if ligne:
            producer.send("log-topic", ligne.encode("utf-8"))
            print("Envoyé :", ligne)
            time.sleep(1)  # simulate real-time
