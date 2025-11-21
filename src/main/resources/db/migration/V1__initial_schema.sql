-- V1__initial_schema.sql
-- Initial schema for Mechongo backend (MySQL 8+)
-- UUIDs stored as CHAR(36), money as DECIMAL(19,4), spatial as geometry(Point,4326)

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE;
SET SQL_MODE='ALLOW_INVALID_DATES';

-- ----------------------------------------------------------------
-- Helper columns common to many tables:
-- id CHAR(36) PRIMARY KEY
-- created_at TIMESTAMP
-- updated_at TIMESTAMP
-- version BIGINT
-- ----------------------------------------------------------------

-- 1) users
CREATE TABLE IF NOT EXISTS users (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  phone VARCHAR(20),
  password_hash VARCHAR(512),
  role VARCHAR(64) NOT NULL,
  status VARCHAR(64) NOT NULL,
  profile_pic_url VARCHAR(1000),
  is_active BOOLEAN DEFAULT TRUE,
  UNIQUE KEY uk_user_email (email),
  UNIQUE KEY uk_user_phone (phone),
  INDEX idx_user_email (email),
  INDEX idx_user_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) user_profiles
CREATE TABLE IF NOT EXISTS user_profiles (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  first_name VARCHAR(150),
  last_name VARCHAR(150),
  display_name VARCHAR(200),
  date_of_birth VARCHAR(20),
  gender VARCHAR(32),
  bio VARCHAR(1000),
  profile_pic_url VARCHAR(1000),
  preferred_language VARCHAR(50),
  locale VARCHAR(32),
  INDEX idx_userprofile_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) user_kyc
CREATE TABLE IF NOT EXISTS user_kyc (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  kyc_id_number VARCHAR(128),
  kyc_type VARCHAR(64),
  document_url VARCHAR(1000),
  verification_status VARCHAR(64),
  rejection_reason VARCHAR(1000),
  INDEX idx_userkyc_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) user_addresses
-- 4) user_addresses
CREATE TABLE IF NOT EXISTS user_addresses (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  label VARCHAR(64),
  address_line1 VARCHAR(500),
  address_line2 VARCHAR(500),
  city VARCHAR(128),
  state VARCHAR(128),
  country VARCHAR(128),
  postal_code VARCHAR(32),
  location POINT SRID 4326 NULL,
  is_default BOOLEAN DEFAULT FALSE,
  INDEX idx_useraddress_user (user_id),
  INDEX idx_useraddress_label (label)
  -- No SPATIAL INDEX here because location is nullable
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) user_settings
CREATE TABLE IF NOT EXISTS user_settings (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  notifications_enabled BOOLEAN DEFAULT TRUE,
  sms_enabled BOOLEAN DEFAULT TRUE,
  email_enabled BOOLEAN DEFAULT TRUE,
  preferred_currency VARCHAR(16) DEFAULT 'INR',
  preferred_payment_method VARCHAR(64),
  receive_promos BOOLEAN DEFAULT TRUE,
  INDEX idx_usersettings_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6) refresh_tokens
CREATE TABLE IF NOT EXISTS refresh_tokens (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  token VARCHAR(512) NOT NULL UNIQUE,
  user_id CHAR(36) NOT NULL,
  expires_at TIMESTAMP NULL,
  revoked BOOLEAN DEFAULT FALSE,
  device_info VARCHAR(1024),
  INDEX idx_rt_token (token),
  INDEX idx_rt_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7) login_history
CREATE TABLE IF NOT EXISTS login_history (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  login_at TIMESTAMP,
  logout_at TIMESTAMP,
  ip_address VARCHAR(128),
  user_agent VARCHAR(1024),
  successful BOOLEAN DEFAULT TRUE,
  refresh_token_id CHAR(36),
  failure_reason VARCHAR(255),
  INDEX idx_login_user (user_id),
  INDEX idx_login_ip (ip_address),
  INDEX idx_login_token (refresh_token_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8) otp_verifications
CREATE TABLE IF NOT EXISTS otp_verifications (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  target VARCHAR(255) NOT NULL,
  purpose VARCHAR(128) NOT NULL,
  code_hash VARCHAR(512) NOT NULL,
  attempts INT DEFAULT 0,
  expires_at TIMESTAMP,
  consumed BOOLEAN DEFAULT FALSE,
  channel VARCHAR(64),
  metadata LONGTEXT,
  INDEX idx_otp_user (user_id),
  INDEX idx_otp_target (target),
  INDEX idx_otp_code_hash (code_hash)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9) mechanic_profiles
CREATE TABLE IF NOT EXISTS mechanic_profiles (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  current_location POINT SRID 4326 NOT NULL,
  availability VARCHAR(64) NOT NULL,
  rating DOUBLE,
  experience_years INT,
  primary_speciality VARCHAR(255),
  service_radius_meters INT,
  verified BOOLEAN DEFAULT FALSE,
  INDEX idx_mech_user (user_id),
  INDEX idx_mech_availability (availability),
  SPATIAL INDEX idx_mech_location (current_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10) mechanic_skills
CREATE TABLE IF NOT EXISTS mechanic_skills (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  skill VARCHAR(200) NOT NULL,
  level VARCHAR(64),
  years_experience INT,
  notes VARCHAR(1000),
  UNIQUE KEY uk_mech_skill (mechanic_id, skill),
  INDEX idx_mechskill_mechanic (mechanic_id),
  INDEX idx_mechskill_skill (skill)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 11) mechanic_documents
CREATE TABLE IF NOT EXISTS mechanic_documents (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  document_type VARCHAR(128) NOT NULL,
  document_url VARCHAR(1000) NOT NULL,
  verification_status VARCHAR(64),
  note VARCHAR(1000),
  INDEX idx_mechdocs_mechanic (mechanic_id),
  INDEX idx_mechdocs_type (document_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 12) mechanic_garages
CREATE TABLE IF NOT EXISTS mechanic_garages (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  name VARCHAR(255) NOT NULL,
  address_line1 VARCHAR(500),
  address_line2 VARCHAR(500),
  city VARCHAR(128),
  state VARCHAR(128),
  country VARCHAR(128),
  postal_code VARCHAR(32),
  location POINT SRID 4326 NOT NULL,
  opening_hours VARCHAR(255),
  contact_number VARCHAR(64),
  is_primary BOOLEAN DEFAULT FALSE,
  INDEX idx_garage_mechanic (mechanic_id),
  INDEX idx_garage_name (name),
  SPATIAL INDEX idx_garage_location (location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 13) mechanic_rating_summary
CREATE TABLE IF NOT EXISTS mechanic_rating_summary (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  total_ratings INT DEFAULT 0,
  total_reviews INT DEFAULT 0,
  average_rating DOUBLE DEFAULT 0,
  last_recalculated_at TIMESTAMP,
  INDEX idx_rating_summary_mechanic (mechanic_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 14) sos_requests
CREATE TABLE IF NOT EXISTS sos_requests (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  requester_id CHAR(36) NOT NULL,
  assigned_mechanic_id CHAR(36),
  pickup_location POINT SRID 4326 NOT NULL,
  drop_location POINT SRID 4326 NULL,
  description VARCHAR(2000),
  status VARCHAR(64) NOT NULL,
  estimated_amount DECIMAL(19,4),
  actual_amount DECIMAL(19,4),
  priority INT DEFAULT 5,
  scheduled_at TIMESTAMP,
  expected_arrival_at TIMESTAMP,
  canceled_by VARCHAR(36),
  cancel_reason VARCHAR(1000),
  INDEX idx_sos_requester (requester_id),
  INDEX idx_sos_status (status),
  INDEX idx_sos_assigned_mechanic (assigned_mechanic_id),
  SPATIAL INDEX idx_sos_pickup (pickup_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 15) sos_status_history
CREATE TABLE IF NOT EXISTS sos_status_history (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  sos_request_id CHAR(36) NOT NULL,
  status VARCHAR(64) NOT NULL,
  notes VARCHAR(1000),
  changed_by VARCHAR(36),
  INDEX idx_sos_status_history_sos (sos_request_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 16) sos_locations
CREATE TABLE IF NOT EXISTS sos_locations (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  sos_request_id CHAR(36) NOT NULL,
  location_type VARCHAR(64),
  location POINT SRID 4326 NOT NULL,
  note VARCHAR(500),
  INDEX idx_sos_location_sos (sos_request_id),
  INDEX idx_sos_location_type (location_type),
  SPATIAL INDEX idx_sos_location_point (location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 17) sos_assignments
CREATE TABLE IF NOT EXISTS sos_assignments (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  sos_request_id CHAR(36) NOT NULL,
  mechanic_id CHAR(36) NOT NULL,
  assignment_status VARCHAR(64) NOT NULL,
  distance_meters DOUBLE,
  note VARCHAR(500),
  INDEX idx_sos_assignment_sos (sos_request_id),
  INDEX idx_sos_assignment_mechanic (mechanic_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 18) sos_events
CREATE TABLE IF NOT EXISTS sos_events (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  sos_request_id CHAR(36) NOT NULL,
  event_type VARCHAR(128) NOT NULL,
  data_json LONGTEXT,
  performed_by VARCHAR(36),
  INDEX idx_sos_event_sos (sos_request_id),
  INDEX idx_sos_event_type (event_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 19) payment_details
CREATE TABLE IF NOT EXISTS payment_details (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  sos_request_id CHAR(36),
  method VARCHAR(32),
  provider VARCHAR(32),
  provider_txn_id VARCHAR(255),
  amount DECIMAL(19,4) NOT NULL,
  status VARCHAR(32) NOT NULL,
  currency VARCHAR(8) DEFAULT 'INR',
  captured_at TIMESTAMP,
  metadata LONGTEXT,
  INDEX idx_payment_user (user_id),
  INDEX idx_payment_sos (sos_request_id),
  INDEX idx_payment_provider_txn (provider_txn_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 20) refunds
CREATE TABLE IF NOT EXISTS refunds (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  payment_detail_id CHAR(36),
  provider_txn_id VARCHAR(255),
  amount DECIMAL(19,4) NOT NULL,
  status VARCHAR(32) NOT NULL,
  requested_at TIMESTAMP,
  processed_at TIMESTAMP,
  reason VARCHAR(1000),
  metadata LONGTEXT,
  INDEX idx_refund_user (user_id),
  INDEX idx_refund_payment (payment_detail_id),
  INDEX idx_refund_provider_txn (provider_txn_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 21) wallet_balances
CREATE TABLE IF NOT EXISTS wallet_balances (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  wallet_type VARCHAR(32) NOT NULL,
  balance DECIMAL(19,4) NOT NULL DEFAULT 0,
  UNIQUE KEY uk_wallet_user_type (user_id, wallet_type),
  INDEX idx_wallet_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 22) wallet_transactions
CREATE TABLE IF NOT EXISTS wallet_transactions (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  wallet_type VARCHAR(32) NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  transaction_type VARCHAR(32) NOT NULL,
  reference_id VARCHAR(255),
  description VARCHAR(1000),
  INDEX idx_wtxn_user (user_id),
  INDEX idx_wtxn_type (wallet_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 23) reviews
CREATE TABLE IF NOT EXISTS reviews (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  user_id CHAR(36) NOT NULL,
  sos_request_id CHAR(36),
  rating INT NOT NULL,
  title VARCHAR(255),
  comment VARCHAR(2000),
  visible BOOLEAN DEFAULT TRUE,
  reported BOOLEAN DEFAULT FALSE,
  report_reason VARCHAR(1000),
  INDEX idx_review_mechanic (mechanic_id),
  INDEX idx_review_user (user_id),
  INDEX idx_review_sos (sos_request_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 24) feedbacks
CREATE TABLE IF NOT EXISTS feedbacks (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  category VARCHAR(128),
  subject VARCHAR(255),
  message LONGTEXT,
  handled BOOLEAN DEFAULT FALSE,
  source VARCHAR(64),
  INDEX idx_feedback_user (user_id),
  INDEX idx_feedback_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 25) review_moderation
CREATE TABLE IF NOT EXISTS review_moderation (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  review_id CHAR(36) NOT NULL,
  moderator_id CHAR(36),
  action VARCHAR(64) NOT NULL,
  old_rating INT,
  new_rating INT,
  reason VARCHAR(2000),
  actioned_at TIMESTAMP,
  INDEX idx_moderation_review (review_id),
  INDEX idx_moderation_moderator (moderator_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 26) user_documents
CREATE TABLE IF NOT EXISTS user_documents (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36) NOT NULL,
  document_type VARCHAR(64) NOT NULL,
  document_url VARCHAR(1000) NOT NULL,
  verification_status VARCHAR(32) NOT NULL,
  remarks VARCHAR(1000),
  INDEX idx_user_document_user (user_id),
  INDEX idx_user_document_type (document_type),
  INDEX idx_user_document_status (verification_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 27) notification_templates
CREATE TABLE IF NOT EXISTS notification_templates (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  name VARCHAR(255) NOT NULL,
  channel VARCHAR(32) NOT NULL,
  subject VARCHAR(500),
  body LONGTEXT,
  enabled BOOLEAN DEFAULT TRUE,
  remarks VARCHAR(1000),
  INDEX idx_template_name (name),
  INDEX idx_template_channel (channel)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 28) push_tokens
CREATE TABLE IF NOT EXISTS push_tokens (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  token_text LONGTEXT NOT NULL,
  token_hash CHAR(64) NOT NULL,
  platform VARCHAR(50),
  active BOOLEAN DEFAULT TRUE,
  metadata LONGTEXT,
  UNIQUE KEY uk_push_token_hash (token_hash),
  INDEX idx_push_token_user (user_id),
  INDEX idx_push_token_platform (platform)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 29) attachments
CREATE TABLE IF NOT EXISTS attachments (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  owner_id CHAR(36),
  attachment_type VARCHAR(64),
  file_url VARCHAR(1500) NOT NULL,
  original_name VARCHAR(255),
  file_size BIGINT,
  content_type VARCHAR(256),
  metadata LONGTEXT,
  INDEX idx_attachment_owner (owner_id),
  INDEX idx_attachment_type (attachment_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 30) activity_logs
CREATE TABLE IF NOT EXISTS activity_logs (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  user_id CHAR(36),
  event_type VARCHAR(128) NOT NULL,
  metadata LONGTEXT,
  INDEX idx_activity_user (user_id),
  INDEX idx_activity_event (event_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 31) metric_events
CREATE TABLE IF NOT EXISTS metric_events (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  metric_type VARCHAR(128) NOT NULL,
  category VARCHAR(128),
  value VARCHAR(255),
  data LONGTEXT,
  INDEX idx_metric_type (metric_type),
  INDEX idx_metric_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 32) system_settings
CREATE TABLE IF NOT EXISTS system_settings (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  setting_key VARCHAR(255) NOT NULL,
  setting_value LONGTEXT NOT NULL,
  description VARCHAR(1000),
  editable BOOLEAN DEFAULT TRUE,
  UNIQUE KEY uk_setting_key (setting_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 33) error_logs
CREATE TABLE IF NOT EXISTS error_logs (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  error_level VARCHAR(32),
  error_code VARCHAR(64),
  message VARCHAR(2000),
  stack_trace LONGTEXT,
  metadata LONGTEXT,
  INDEX idx_error_level (error_level),
  INDEX idx_error_code (error_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 34) api_request_logs
CREATE TABLE IF NOT EXISTS api_request_logs (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  http_method VARCHAR(16),
  path VARCHAR(1000),
  status_code INT,
  ip_address VARCHAR(64),
  latency_ms BIGINT,
  request_body LONGTEXT,
  response_body LONGTEXT,
  headers LONGTEXT,
  metadata LONGTEXT,
  INDEX idx_api_method (http_method),
  INDEX idx_api_status (status_code),
  INDEX idx_api_path (path)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 35) payout_requests
CREATE TABLE IF NOT EXISTS payout_requests (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  mechanic_id CHAR(36) NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  method VARCHAR(64),
  provider_txn_id VARCHAR(255),
  status VARCHAR(64) NOT NULL,
  requested_at TIMESTAMP,
  processed_at TIMESTAMP,
  failure_reason VARCHAR(1000),
  metadata LONGTEXT,
  INDEX idx_payout_mechanic (mechanic_id),
  INDEX idx_payout_status (status),
  INDEX idx_payout_provider_txn (provider_txn_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 36) referrals
CREATE TABLE IF NOT EXISTS referrals (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  referrer_id CHAR(36),
  referred_id CHAR(36),
  referral_code VARCHAR(64),
  rewarded BOOLEAN DEFAULT FALSE,
  rewarded_at TIMESTAMP,
  metadata LONGTEXT,
  UNIQUE KEY uk_referrer_referred (referrer_id, referred_id),
  INDEX idx_referrer (referrer_id),
  INDEX idx_referred (referred_id),
  INDEX idx_code (referral_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 37) chat_messages
CREATE TABLE IF NOT EXISTS chat_messages (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  sos_request_id CHAR(36),
  sender_id CHAR(36),
  message LONGTEXT,
  attachment_url VARCHAR(1500),
  message_type VARCHAR(64),
  seen BOOLEAN DEFAULT FALSE,
  INDEX idx_chat_sos (sos_request_id),
  INDEX idx_chat_sender (sender_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 38) audit_logs
CREATE TABLE IF NOT EXISTS audit_logs (
  id CHAR(36) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  version BIGINT,
  entity_name VARCHAR(255) NOT NULL,
  entity_id VARCHAR(255) NOT NULL,
  action VARCHAR(255) NOT NULL,
  payload LONGTEXT,
  user_id VARCHAR(255),
  INDEX idx_audit_entity (entity_name, entity_id),
  INDEX idx_audit_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 39) notification_templates (already created above as 27, safe to keep)
-- 40) push_tokens (already created above as 28)
-- 41) attachments (already created above as 29)
-- (All other modules covered)

-- Restore session vars
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
