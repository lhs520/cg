insert into permission(permission_id,permission_name,permission_description)
values(1,"staff:all","员工管理权限");

insert into role_permission
(role_id,permission_id)
values(1,1),(2,1);