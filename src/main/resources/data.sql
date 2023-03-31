INSERT INTO Agent (id, name, type ) VALUES (1, 'Agent 1', 'TYPE1');
INSERT INTO Agent (id, name, type ) VALUES (2, 'Agent 2', 'TYPE2');

INSERT INTO Route (id, name, agent_id) VALUES (1, 'Route 1', 1);
INSERT INTO Route (id, name, agent_id) VALUES (2, 'Route 2', 2);
INSERT INTO Route (id, name, agent_id) VALUES (3, 'Route 3', 1);

INSERT INTO Module (id, name, type) VALUES (1, 'module1', 'INPUT');
INSERT INTO Module (id, name, type) VALUES (2, 'module2', 'OUTPUT');
INSERT INTO Module (id, name, type) VALUES (3, 'module3', 'PROCESSOR');

INSERT INTO Module_Route (module_id, route_id) VALUES (1, 1);
INSERT INTO Module_Route (module_id, route_id) VALUES (2, 1);
INSERT INTO Module_Route (module_id, route_id) VALUES (2, 2);
INSERT INTO Module_Route (module_id, route_id) VALUES (3, 2);