--insert into vehicle (vehicle_id, name, model) values (1, 'Vehicle3', 'MODEL3');
--
insert into vehicle (name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
values ('Vehicle1', 'MODEL1', 'VRN1', 'ENGINE_FAULT', 3, 2);

insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
values ('Vehicle2', 'MODEL2', 'VRN2', 'OVER_HEATING', 1, 1);

insert into vehicle (name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
values ('Vehicle3', 'MODEL3', 'VRN3', 'OTHER', 2, 1);

insert into vehicle (name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
values ('Vehicle4', 'MODEL4', 'VRN4', 'BREAK_SYSTEM_FAULT', 0, 1);
--
--insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
--values ('Vehicle5', 'MODEL5', 'VRN5', 'BREAK_SYSTEM_FAULT', 1, 0);
--
--insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
--values ('Vehicle6', 'MODEL6', 'VRN6', 'NEEDS_MAINTENANCE', 0, 1);
--
--insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
--values ('Vehicle7', 'MODEL7', 'VRN7', 'NEEDS_MAINTENANCE', 1, 1);
--
--insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
--values ('Vehicle8', 'MODEL8', 'VRN8', 'NEEDS_MAINTENANCE', 0, 1);
--
--insert into vehicle ( name, model, vrn, vehicle_health_status, number_of_faults, vehicle_status)
--values ('Vehicle9', 'MODEL9', 'VRN9', 'NEEDS_MAINTENANCE', 0, 1);


insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
values(1, 1, 'NEEDS_MAINTENANCE', 'Needs tyre checks',CURRENT_TIMESTAMP);

insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
values(2, 1, 'BREAK_SYSTEM_FAULT', 'Problem with brake performance',CURRENT_TIMESTAMP);

insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
values(1, 1, 'NEEDS_MAINTENANCE', 'Needs oil change',CURRENT_TIMESTAMP);

insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
values(3, 1, 'OTHER', 'unable to connect to mobile',CURRENT_TIMESTAMP);

--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(3, 1, 'ENGINE_FAULT', 'its been a while5',CURRENT_TIMESTAMP);
--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(1, 1, 'OVER_HEATING', 'its been a while6',CURRENT_TIMESTAMP);
--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(5, 0, 'ENGINE_FAULT', 'its been a while7',CURRENT_TIMESTAMP);
--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(7, 0, 'OVER_HEATING', 'its been a while8',CURRENT_TIMESTAMP);
--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(1, 1, 'NEEDS_MAINTENANCE', 'its been a while1',CURRENT_TIMESTAMP);
--
--insert into vehicle_events (vehicle_id, event_status, vehicle_event_health_status, fault_description, created_at)
--values(1, 0, 'OVER_HEATING', 'its been a while1',CURRENT_TIMESTAMP);

