insert into schedule_slot (id,date) values (schedule_slot_sq.nextval,sysdate);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'10:00',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'10:10',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'10:20',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'10:30',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'10:40',true);
insert into schedule_slot (id,date) values (schedule_slot_sq.nextval,sysdate + 1);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'11:00',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'11:10',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'11:20',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'11:30',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'11:40',true);
insert into schedule_slot (id,date) values (schedule_slot_sq.nextval,sysdate + 2);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'12:00',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'12:10',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'12:20',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'12:30',true);
insert into schedule_slot_time (id,id_schedule_slot,time,available) values (schedule_slot_time_sq.nextval,schedule_slot_sq.currval,'12:40',true);

